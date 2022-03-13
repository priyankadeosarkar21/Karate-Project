/*
 * The MIT License
 *
 * Copyright 2018 Intuit Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.intuit.karate.core;

import com.intuit.karate.Json;
import com.intuit.karate.StringUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author pthomas3
 */
public class StepResult {

    private static final Map<String, Object> DUMMY_MATCH;

    private final Step step;
    private final Result result;

    private boolean hidden;
    private List<Embed> embeds;
    private List<FeatureResult> callResults;
    private String stepLog;
    private boolean errorIgnored = false;
    private Throwable failedReason;

    public String getErrorMessage() {
        if (result == null) {
            return null;
        }
        Throwable error = result.getError();
        return error == null ? null : error.getMessage();
    }

    public void appendToStepLog(String log) {
        if (log == null) {
            return;
        }
        if (stepLog == null) {
            stepLog = "";
        }
        stepLog = stepLog + log;
    }

    public void setStepLog(String stepLog) {
        this.stepLog = stepLog;
    }

    public void setCallResults(List<FeatureResult> callResults) {
        this.callResults = callResults;
    }

    public void addEmbeds(List<Embed> value) {
        if (value != null) {
            if (embeds == null) {
                embeds = new ArrayList();
            }
            embeds.addAll(value);
        }
    }

    public void setCallResultsFromKarateJson(File workingDir, List<Map<String, Object>> list) {
        if (list != null) {
            callResults = new ArrayList(list.size());
            for (Map<String, Object> map : list) {
                FeatureResult fr = FeatureResult.fromKarateJson(workingDir, map);
                callResults.add(fr);
            }
        }
    }

    public static StepResult fromKarateJson(File workingDir, Scenario scenario, Map<String, Object> map) {
        Map<String, Object> stepMap = (Map) map.get("step");
        Step step = Step.fromKarateJson(scenario, stepMap);
        Result result = Result.fromKarateJson((Map) map.get("result"));
        StepResult sr = new StepResult(step, result);
        Boolean hidden = (Boolean) map.get("hidden");
        if (hidden != null) {
            sr.setHidden(hidden);
        }
        String stepLog = (String) map.get("stepLog");
        sr.setStepLog(stepLog);
        List<Map<String, Object>> embedsList = (List) map.get("embeds");
        if (embedsList != null) {
            List<Embed> embeds = new ArrayList(embedsList.size());
            for (Map<String, Object> embedMap : embedsList) {
                Embed embed = Embed.fromKarateJson(embedMap);
                embeds.add(embed);
            }
            sr.addEmbeds(embeds);
        }
        sr.setCallResultsFromKarateJson(workingDir, (List) map.get("callResults"));
        return sr;
    }

    public Map<String, Object> toKarateJson() {
        Map<String, Object> map = new HashMap();
        map.put("step", step.toKarateJson());
        map.put("result", result.toKarateJson());
        if (hidden) {
            map.put("hidden", hidden);
        }
        if (!StringUtils.isBlank(stepLog)) {
            map.put("stepLog", stepLog);
        }
        if (embeds != null && !embeds.isEmpty()) {
            List<Map<String, Object>> list = new ArrayList(embeds.size());
            map.put("embeds", list);
            for (Embed embed : embeds) {
                list.add(embed.toKarateJson());
            }
        }
        if (callResults != null && !callResults.isEmpty()) {
            List<Map<String, Object>> list = new ArrayList(callResults.size());
            map.put("callResults", list);
            for (FeatureResult fr : callResults) {
                list.add(Json.of(fr.toKarateJson()).asMap());
            }
        }
        return map;
    }

    private static List<Map> tableToCucumberJson(Table table) {
        List<List<String>> rows = table.getRows();
        List<Map> list = new ArrayList(rows.size());
        int count = rows.size();
        for (int i = 0; i < count; i++) {
            List<String> row = rows.get(i);
            Map<String, Object> map = new HashMap(2);
            map.put("cells", row);
            map.put("line", table.getLineNumberForRow(i));
            list.add(map);
        }
        return list;
    }

    public Map<String, Object> toCucumberJson() {
        Map<String, Object> map = new HashMap(8);
        map.put("line", step.getLine());
        map.put("keyword", step.getPrefix());
        map.put("name", step.getText());
        map.put("result", result.toCucumberJson());
        map.put("match", DUMMY_MATCH);
        StringBuilder sb = new StringBuilder();
        if (step.getDocString() != null) {
            sb.append(step.getDocString());
        }
        if (stepLog != null) {
            sb.append(stepLog);
        }
        if (sb.length() > 0) {
            map.put("doc_string", docStringToCucumberJson(step.getLine(), sb.toString()));
        }
        if (step.getTable() != null) {
            map.put("rows", tableToCucumberJson(step.getTable()));
        }
        if (embeds != null) {
            List<Map> embedList = new ArrayList(embeds.size());
            for (Embed embed : embeds) {
                embedList.add(embed.toMap());
            }
            map.put("embeddings", embedList);
        }
        if (step.getComments() != null && !step.getComments().isEmpty()) {
            map.put("comments", step.getComments());
        }
        return map;
    }

    static {
        DUMMY_MATCH = new HashMap(2);
        DUMMY_MATCH.put("location", "karate");
        DUMMY_MATCH.put("arguments", Collections.EMPTY_LIST);
    }

    private static Map<String, Object> docStringToCucumberJson(int line, String text) {
        Map<String, Object> map = new HashMap(3);
        map.put("content_type", "");
        map.put("line", line);
        map.put("value", text);
        return map;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isHidden() {
        return hidden;
    }

    public boolean isWithCallResults() {
        return callResults != null && !callResults.isEmpty();
    }

    public boolean isStopped() {
        return result.isFailed() || result.isAborted();
    }

    public StepResult(Step step, Result result) {
        this.step = step;
        this.result = result;
    }

    public Step getStep() {
        return step;
    }

    public Result getResult() {
        return result;
    }

    public String getStepLog() {
        return stepLog;
    }

    public List<Embed> getEmbeds() {
        return embeds;
    }

    public void addEmbed(Embed embed) {
        if (embeds == null) {
            embeds = new ArrayList();
        }
        embeds.add(embed);
    }

    public void addCallResults(List<FeatureResult> values) {
        if (callResults == null) {
            callResults = new ArrayList();
        }
        callResults.addAll(values);
    }

    public List<FeatureResult> getCallResults() {
        return callResults;
    }

    public boolean isErrorIgnored() {
        return errorIgnored;
    }

    public void setErrorIgnored(boolean errorIgnored) {
        this.errorIgnored = errorIgnored;
    }

    public Throwable getFailedReason() {
        return failedReason;
    }

    public void setFailedReason(Throwable failedReason) {
        this.failedReason = failedReason;
    }

    @Override
    public String toString() {
        return "[" + result + "] " + step;
    }

}
