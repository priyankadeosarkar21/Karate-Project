/*
 * The MIT License
 *
 * Copyright 2020 Intuit Inc.
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
package com.intuit.karate.robot.win;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author pthomas3
 */
public enum Property {

    RuntimeId(30000),
    BoundingRectangle(30001),
    ProcessId(30002),
    ControlType(30003),
    LocalizedControlType(30004),
    Name(30005),
    AcceleratorKey(30006),
    AccessKey(30007),
    HasKeyboardFocus(30008),
    IsKeyboardFocusable(30009),
    IsEnabled(30010),
    AutomationId(30011),
    ClassName(30012),
    HelpText(30013),
    ClickablePoint(30014),
    Culture(30015),
    IsControlElement(30016),
    IsContentElement(30017),
    LabeledBy(30018),
    IsPassword(30019),
    NativeWindowHandle(30020),
    ItemType(30021),
    IsOffscreen(30022),
    Orientation(30023),
    FrameworkId(30024),
    IsRequiredForForm(30025),
    ItemStatus(30026),
    IsDockPatternAvailable(30027),
    IsExpandCollapsePatternAvailable(30028),
    IsGridItemPatternAvailable(30029),
    IsGridPatternAvailable(30030),
    IsInvokePatternAvailable(30031),
    IsMultipleViewPatternAvailable(30032),
    IsRangeValuePatternAvailable(30033),
    IsScrollPatternAvailable(30034),
    IsScrollItemPatternAvailable(30035),
    IsSelectionItemPatternAvailable(30036),
    IsSelectionPatternAvailable(30037),
    IsTablePatternAvailable(30038),
    IsTableItemPatternAvailable(30039),
    IsTextPatternAvailable(30040),
    IsTogglePatternAvailable(30041),
    IsTransformPatternAvailable(30042),
    IsValuePatternAvailable(30043),
    IsWindowPatternAvailable(30044),
    ValueValue(30045),
    ValueIsReadOnly(30046),
    RangeValueValue(30047),
    RangeValueIsReadOnly(30048),
    RangeValueMinimum(30049),
    RangeValueMaximum(30050),
    RangeValueLargeChange(30051),
    RangeValueSmallChange(30052),
    ScrollHorizontalScrollPercent(30053),
    ScrollHorizontalViewSize(30054),
    ScrollVerticalScrollPercent(30055),
    ScrollVerticalViewSize(30056),
    ScrollHorizontallyScrollable(30057),
    ScrollVerticallyScrollable(30058),
    SelectionSelection(30059),
    SelectionCanSelectMultiple(30060),
    SelectionIsSelectionRequired(30061),
    GridRowCount(30062),
    GridColumnCount(30063),
    GridItemRow(30064),
    GridItemColumn(30065),
    GridItemRowSpan(30066),
    GridItemColumnSpan(30067),
    GridItemContainingGrid(30068),
    DockDockPosition(30069),
    ExpandCollapseExpandCollapseState(30070),
    MultipleViewCurrentView(30071),
    MultipleViewSupportedViews(30072),
    WindowCanMaximize(30073),
    WindowCanMinimize(30074),
    WindowWindowVisualState(30075),
    WindowWindowInteractionState(30076),
    WindowIsModal(30077),
    WindowIsTopmost(30078),
    SelectionItemIsSelected(30079),
    SelectionItemSelectionContainer(30080),
    TableRowHeaders(30081),
    TableColumnHeaders(30082),
    TableRowOrColumnMajor(30083),
    TableItemRowHeaderItems(30084),
    TableItemColumnHeaderItems(30085),
    ToggleToggleState(30086),
    TransformCanMove(30087),
    TransformCanResize(30088),
    TransformCanRotate(30089),
    IsLegacyIAccessiblePatternAvailable(30090),
    LegacyIAccessibleChildId(30091),
    LegacyIAccessibleName(30092),
    LegacyIAccessibleValue(30093),
    LegacyIAccessibleDescription(30094),
    LegacyIAccessibleRole(30095),
    LegacyIAccessibleState(30096),
    LegacyIAccessibleHelp(30097),
    LegacyIAccessibleKeyboardShortcut(30098),
    LegacyIAccessibleSelection(30099),
    LegacyIAccessibleDefaultAction(30100),
    AriaRole(30101),
    AriaProperties(30102),
    IsDataValidForForm(30103),
    ControllerFor(30104),
    DescribedBy(30105),
    FlowsTo(30106),
    ProviderDescription(30107),
    IsItemContainerPatternAvailable(30108),
    IsVirtualizedItemPatternAvailable(30109),
    IsSynchronizedInputPatternAvailable(30110),
    OptimizeForVisualContent(30111),
    IsObjectModelPatternAvailable(30112),
    AnnotationAnnotationTypeId(30113),
    AnnotationAnnotationTypeName(30114),
    AnnotationAuthor(30115),
    AnnotationDateTime(30116),
    AnnotationTarget(30117),
    IsAnnotationPatternAvailable(30118),
    IsTextPattern2Available(30119),
    StylesStyleId(30120),
    StylesStyleName(30121),
    StylesFillColor(30122),
    StylesFillPatternStyle(30123),
    StylesShape(30124),
    StylesFillPatternColor(30125),
    StylesExtendedProperties(30126),
    IsStylesPatternAvailable(30127),
    IsSpreadsheetPatternAvailable(30128),
    SpreadsheetItemFormula(30129),
    SpreadsheetItemAnnotationObjects(30130),
    SpreadsheetItemAnnotationTypes(30131),
    IsSpreadsheetItemPatternAvailable(30132),
    Transform2CanZoom(30133),
    IsTransformPattern2Available(30134),
    LiveSetting(30135),
    IsTextChildPatternAvailable(30136),
    IsDragPatternAvailable(30137),
    DragIsGrabbed(30138),
    DragDropEffect(30139),
    DragDropEffects(30140),
    IsDropTargetPatternAvailable(30141),
    DropTargetDropTargetEffect(30142),
    DropTargetDropTargetEffects(30143),
    DragGrabbedItems(30144),
    Transform2ZoomLevel(30145),
    Transform2ZoomMinimum(30146),
    Transform2ZoomMaximum(30147),
    FlowsFrom(30148),
    IsTextEditPatternAvailable(30149),
    IsPeripheral(30150),
    IsCustomNavigationPatternAvailable(30151),
    PositionInSet(30152),
    SizeOfSet(30153),
    Level(30154),
    AnnotationTypes(30155),
    AnnotationObjects(30156);

    public final int value;

    private Property(int value) {
        this.value = value;
    }

    private static final Map<Integer, Property> FROM_ID;

    static {
        Property[] values = Property.values();
        FROM_ID = new HashMap(values.length);
        for (Property p : values) {
            FROM_ID.put(p.value, p);
        }
    }
    
    public static Property fromId(int id) {
        return FROM_ID.get(id);
    }

}
