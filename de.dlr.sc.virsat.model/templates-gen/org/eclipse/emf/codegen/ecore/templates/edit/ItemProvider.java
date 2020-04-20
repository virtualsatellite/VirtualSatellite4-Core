package org.eclipse.emf.codegen.ecore.templates.edit;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class ItemProvider
{
  protected static String nl;
  public static synchronized ItemProvider create(String lineSeparator)
  {
    nl = lineSeparator;
    ItemProvider result = new ItemProvider();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + NL;
  protected final String TEXT_3 = NL;
  protected final String TEXT_4 = "/**";
  protected final String TEXT_5 = NL + " * ";
  protected final String TEXT_6 = NL + " */" + NL + "package ";
  protected final String TEXT_7 = ";" + NL + NL;
  protected final String TEXT_8 = NL + NL + "/**" + NL + " * This is the item provider adapter for a {@link ";
  protected final String TEXT_9 = "} object." + NL + " * <!-- begin-user-doc -->" + NL + " * <!-- end-user-doc -->" + NL + " * @generated" + NL + " */" + NL + "public class ";
  protected final String TEXT_10 = " ";
  protected final String TEXT_11 = "extends ";
  protected final String TEXT_12 = NL + "\textends ";
  protected final String TEXT_13 = NL + "\timplements";
  protected final String TEXT_14 = NL + "\t\t";
  protected final String TEXT_15 = ",";
  protected final String TEXT_16 = NL + "{";
  protected final String TEXT_17 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_18 = " copyright = ";
  protected final String TEXT_19 = ";";
  protected final String TEXT_20 = NL + "\t/**" + NL + "\t * This constructs an instance from a factory and a notifier." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_21 = "(AdapterFactory adapterFactory)" + NL + "\t{" + NL + "\t\tsuper(adapterFactory);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * This returns the property descriptors for the adapted class." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_22 = NL + "\t@Override";
  protected final String TEXT_23 = NL + "\tpublic ";
  protected final String TEXT_24 = " getPropertyDescriptors(Object object)" + NL + "\t{" + NL + "\t\tif (itemPropertyDescriptors == null)" + NL + "\t\t{" + NL + "\t\t\tsuper.getPropertyDescriptors(object);" + NL;
  protected final String TEXT_25 = NL + "\t\t\tadd";
  protected final String TEXT_26 = "PropertyDescriptor(object);";
  protected final String TEXT_27 = NL + "\t\t}" + NL + "\t\treturn itemPropertyDescriptors;" + NL + "\t}" + NL;
  protected final String TEXT_28 = NL + "\t/**" + NL + "\t * This adds a property descriptor for the ";
  protected final String TEXT_29 = " feature." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected void add";
  protected final String TEXT_30 = "PropertyDescriptor(Object object)" + NL + "\t{";
  protected final String TEXT_31 = NL + "\t\titemPropertyDescriptors.add" + NL + "\t\t\t(createItemPropertyDescriptor" + NL + "\t\t\t\t(((";
  protected final String TEXT_32 = ")adapterFactory).getRootAdapterFactory()," + NL + "\t\t\t\t getResourceLocator()," + NL + "\t\t\t\t getString(\"_UI_";
  protected final String TEXT_33 = "_";
  protected final String TEXT_34 = "_feature\"),";
  protected final String TEXT_35 = NL + "\t\t\t\t getString(\"_UI_PropertyDescriptor_description\", \"_UI_";
  protected final String TEXT_36 = "_feature\", \"_UI_";
  protected final String TEXT_37 = "_type\"),";
  protected final String TEXT_38 = NL + "\t\t\t\t getString(\"_UI_";
  protected final String TEXT_39 = "_description\"),";
  protected final String TEXT_40 = NL + "\t\t\t\t ";
  protected final String TEXT_41 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_42 = NL + "\t\t\t\t null,";
  protected final String TEXT_43 = ".";
  protected final String TEXT_44 = NL + "\t\t\t\t getString(\"";
  protected final String TEXT_45 = "\"),";
  protected final String TEXT_46 = NL + "\t\t\t\t null));";
  protected final String TEXT_47 = NL + "\t\t\t\t new String[] {";
  protected final String TEXT_48 = NL + "\t\t\t\t\t\"";
  protected final String TEXT_49 = "\"";
  protected final String TEXT_50 = NL + "\t\t\t\t }));";
  protected final String TEXT_51 = NL + "\t}" + NL;
  protected final String TEXT_52 = NL + "\t/**" + NL + "\t * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an" + NL + "\t * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or" + NL + "\t * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_53 = " getChildrenFeatures(Object object)" + NL + "\t{" + NL + "\t\tif (childrenFeatures == null)" + NL + "\t\t{" + NL + "\t\t\tsuper.getChildrenFeatures(object);";
  protected final String TEXT_54 = NL + "\t\t\tchildrenFeatures.add(";
  protected final String TEXT_55 = ");";
  protected final String TEXT_56 = NL + "\t\t}" + NL + "\t\treturn childrenFeatures;" + NL + "\t}" + NL;
  protected final String TEXT_57 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_58 = NL + "\tprotected ";
  protected final String TEXT_59 = " getChildFeature(Object object, Object child)" + NL + "\t{" + NL + "\t\t// Check the type of the specified child object and return the proper feature to use for" + NL + "\t\t// adding (see {@link AddCommand}) it as a child." + NL + "" + NL + "\t\treturn super.getChildFeature(object, child);" + NL + "\t}" + NL;
  protected final String TEXT_60 = NL + "\tpublic boolean hasChildren(Object object)" + NL + "\t{" + NL + "\t\treturn hasChildren(object, ";
  protected final String TEXT_61 = ");" + NL + "\t}" + NL;
  protected final String TEXT_62 = NL + "\t/**" + NL + "\t * *********************************" + NL + "\t * VirSat Specific Code Generation" + NL + "\t * *********************************" + NL + "\t * This returns ";
  protected final String TEXT_63 = ".gif." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_64 = NL + "\tpublic Object getImage(Object object)" + NL + "\t{";
  protected final String TEXT_65 = " " + NL + "\t\tObject rtrnObj = overlayImage(object, getResourceLocator().getImage(\"full/obj16/";
  protected final String TEXT_66 = "\")); " + NL + "\t\t" + NL + "\t\t// In case we can find a trace to an object typed by IQualifedName we might have an alternative image" + NL + "\t\tIQualifiedName qualifiedNameObject = null;" + NL + "\t\tif (object instanceof ATypeInstance) {" + NL + "\t\t\tATypeInstance typeInstance = (ATypeInstance) object;" + NL + "\t\t\tqualifiedNameObject = typeInstance.getType();" + NL + "\t\t} else if (object instanceof StructuralElementInstance) { " + NL + "\t\t\tStructuralElementInstance structuralElementInstance = (StructuralElementInstance) object;" + NL + "\t\t\tqualifiedNameObject = structuralElementInstance.getType();\t\t\t" + NL + "\t\t} else if (object instanceof StructuralElement) { " + NL + "\t\t\tqualifiedNameObject = (StructuralElement) object;\t\t" + NL + "\t\t} else if (object instanceof Category) { " + NL + "\t\t\tqualifiedNameObject = (Category) object;\t\t" + NL + "\t\t}" + NL + "\t\t" + NL + "\t\t// In case we could trace an object of IQualifedName we now ask the image registry for an alternative image" + NL + "\t\tif (qualifiedNameObject != null) {" + NL + "\t\t\tString fullQualifiedID = qualifiedNameObject.getFullQualifiedName();" + NL + "\t\t\tURL imageUrl = DVLMEditPlugin.getImageRegistry().get(fullQualifiedID);" + NL + "\t\t\tif (imageUrl != null) {" + NL + "\t\t\t\trtrnObj = overlayImage(object, imageUrl);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn rtrnObj;" + NL + "\t}";
  protected final String TEXT_67 = NL + "\t\treturn overlayImage(object, getResourceLocator().getImage(\"full/obj16/";
  protected final String TEXT_68 = "\")); ";
  protected final String TEXT_69 = NL + "\t}";
  protected final String TEXT_70 = NL + "\tprotected boolean shouldComposeCreationImage() " + NL + "\t{" + NL + "\t\treturn true;" + NL + "\t}" + NL;
  protected final String TEXT_71 = NL + NL + "\t/**" + NL + "\t * *********************************" + NL + "\t * VirSat Specific Code Generation" + NL + "\t * *********************************" + NL + "\t * This returns the label text for the adapted class." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_72 = NL + "\tpublic String getText(Object object)" + NL + "\t{";
  protected final String TEXT_73 = NL + "\treturn getString(\"_UI_";
  protected final String TEXT_74 = "_type\");";
  protected final String TEXT_75 = NL + "\t\tString labelName =((";
  protected final String TEXT_76 = ")object).getName();";
  protected final String TEXT_77 = NL + "\t\tString label =((";
  protected final String TEXT_78 = ")object).";
  protected final String TEXT_79 = "();" + NL + "\t\treturn labelName + \": \" + label;";
  protected final String TEXT_80 = NL + "\t\t\treturn ((";
  protected final String TEXT_81 = ")getStyledText(object)).getString();";
  protected final String TEXT_82 = NL + "\t\t\t";
  protected final String TEXT_83 = "<?, ?>";
  protected final String TEXT_84 = " = (";
  protected final String TEXT_85 = ")object;";
  protected final String TEXT_86 = NL + "\t\t\treturn \"\" + ";
  protected final String TEXT_87 = ".getKey() + \" -> \" + ";
  protected final String TEXT_88 = ".getValue();";
  protected final String TEXT_89 = NL + "\t\t\tString key = crop(\"\" + ";
  protected final String TEXT_90 = ".getKey());";
  protected final String TEXT_91 = NL + "\t\t\tString key = \"\" + ";
  protected final String TEXT_92 = ".getKey();";
  protected final String TEXT_93 = NL + "\t\t\tString value = crop(\"\" + ";
  protected final String TEXT_94 = ".getValue());";
  protected final String TEXT_95 = NL + "\t\t\tString value = \"\" + ";
  protected final String TEXT_96 = NL + "\t\t\treturn key + \" -> \" + value;";
  protected final String TEXT_97 = ")object;" + NL + "\t\t\treturn getString(\"_UI_";
  protected final String TEXT_98 = "_type\") + \" \" + ";
  protected final String TEXT_99 = "();";
  protected final String TEXT_100 = NL + "\t\t\tString label = crop(((";
  protected final String TEXT_101 = "());";
  protected final String TEXT_102 = NL + "\t\t\tString label = ((";
  protected final String TEXT_103 = " labelValue = ((";
  protected final String TEXT_104 = ")object).eGet(";
  protected final String TEXT_105 = NL + "\t\t\tString label = labelValue == null ? null : labelValue.toString();";
  protected final String TEXT_106 = NL + "\t\t\treturn label == null || label.length() == 0 ?" + NL + "\t\t\t\tgetString(\"_UI_";
  protected final String TEXT_107 = "_type\") :";
  protected final String TEXT_108 = NL + "\t\t\t\tgetString(\"_UI_";
  protected final String TEXT_109 = "_type\") + \" \" + label;";
  protected final String TEXT_110 = NL + "\t\t\treturn getString(\"_UI_";
  protected final String TEXT_111 = NL + "\t}" + NL + "\t";
  protected final String TEXT_112 = NL + "\t/**" + NL + "\t * This returns the label styled text for the adapted class." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_113 = NL + "\tpublic Object getStyledText(Object object)" + NL + "\t{";
  protected final String TEXT_114 = NL + "\t\treturn new ";
  protected final String TEXT_115 = "(\"\" + ";
  protected final String TEXT_116 = ".getKey()).append(\" -> \", ";
  protected final String TEXT_117 = ".QUALIFIER_STYLER).append(\"\" + ";
  protected final String TEXT_118 = NL + "\t\tString key = crop(\"\" + ";
  protected final String TEXT_119 = NL + "\t\tString key = \"\" + ";
  protected final String TEXT_120 = NL + "\t\tString value = crop(\"\" + ";
  protected final String TEXT_121 = NL + "\t\tString value = \"\" + ";
  protected final String TEXT_122 = "(key).append(\" -> \", ";
  protected final String TEXT_123 = ".QUALIFIER_STYLER).append(value);";
  protected final String TEXT_124 = ")object;" + NL + "\t\treturn new ";
  protected final String TEXT_125 = "(getString(\"_UI_";
  protected final String TEXT_126 = "_type\"), ";
  protected final String TEXT_127 = ".QUALIFIER_STYLER).append(\" \").append(";
  protected final String TEXT_128 = ".toString(";
  protected final String TEXT_129 = "()));";
  protected final String TEXT_130 = NL + "\t\tString label = crop(((";
  protected final String TEXT_131 = NL + "\t\tString label = ((";
  protected final String TEXT_132 = NL + "\t\tString label = labelValue == null ? null : labelValue.toString();";
  protected final String TEXT_133 = NL + "    \t";
  protected final String TEXT_134 = " styledLabel = new ";
  protected final String TEXT_135 = "();" + NL + "\t\tif (label == null || label.length() == 0) " + NL + "\t\t{" + NL + "\t\t\tstyledLabel.append(getString(\"_UI_";
  protected final String TEXT_136 = ".QUALIFIER_STYLER); ";
  protected final String TEXT_137 = NL + "\t\t} else {" + NL + "\t\t\tstyledLabel.append(getString(\"_UI_";
  protected final String TEXT_138 = ".QUALIFIER_STYLER).append(\" \" + label);";
  protected final String TEXT_139 = NL + "\t\t}" + NL + "\t\treturn styledLabel;";
  protected final String TEXT_140 = "_type\"));";
  protected final String TEXT_141 = NL + "\t}\t";
  protected final String TEXT_142 = NL + NL + "\t/**" + NL + "\t * This handles model notifications by calling {@link #updateChildren} to update any cached" + NL + "\t * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_143 = NL + "\tpublic void notifyChanged(Notification notification)" + NL + "\t{" + NL + "\t\tupdateChildren(notification);";
  protected final String TEXT_144 = NL + NL + "\t\tswitch (notification.getFeatureID(";
  protected final String TEXT_145 = ".class))" + NL + "\t\t{";
  protected final String TEXT_146 = NL + "\t\t\tcase ";
  protected final String TEXT_147 = ":";
  protected final String TEXT_148 = NL + "\t\t\t\tfireNotifyChanged(new ";
  protected final String TEXT_149 = "(notification, notification.getNotifier(), false, true));" + NL + "\t\t\t\treturn;";
  protected final String TEXT_150 = "(notification, notification.getNotifier(), true, false));" + NL + "\t\t\t\treturn;";
  protected final String TEXT_151 = "(notification, notification.getNotifier(), true, true));" + NL + "\t\t\t\treturn;";
  protected final String TEXT_152 = NL + "\t\t}";
  protected final String TEXT_153 = NL + "\t\tsuper.notifyChanged(notification);" + NL + "\t}" + NL;
  protected final String TEXT_154 = NL + "\t/**" + NL + "\t * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children" + NL + "\t * that can be created under this object." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_155 = NL + "\tprotected void collectNewChildDescriptors(";
  protected final String TEXT_156 = " newChildDescriptors, Object object)" + NL + "\t{" + NL + "\t\tsuper.collectNewChildDescriptors(newChildDescriptors, object);";
  protected final String TEXT_157 = NL + NL + "\t\tnewChildDescriptors.add" + NL + "\t\t\t(createChildParameter" + NL + "\t\t\t\t(";
  protected final String TEXT_158 = ".createEntry" + NL + "\t\t\t\t\t(";
  protected final String TEXT_159 = NL + "\t\t\t\t\t ";
  protected final String TEXT_160 = ".create(";
  protected final String TEXT_161 = "))));";
  protected final String TEXT_162 = ".create";
  protected final String TEXT_163 = "())));";
  protected final String TEXT_164 = ")));";
  protected final String TEXT_165 = ".createFromString(";
  protected final String TEXT_166 = ", ";
  protected final String TEXT_167 = " // TODO: ensure this is a valid literal value";
  protected final String TEXT_168 = "));";
  protected final String TEXT_169 = NL + "\t@Override" + NL + "\tprotected Command createRemoveCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Collection<?> collection) {" + NL + "\t\t// In case that a Type Instance is inherited it will have some Super TIs being set." + NL + "\t\t// Then the command for removing the TI should fail, because the inheritance" + NL + "\t\t// builder would regenerate it anyway" + NL + "\t\tfor (Object obj : collection) {" + NL + "\t\t\tif (obj instanceof ATypeInstance) {" + NL + "\t\t\t\tATypeInstance ti = (ATypeInstance) obj;" + NL + "\t\t\t\tif (!ti.getSuperTis().isEmpty()) {" + NL + "\t\t\t\t\treturn UnexecutableCommand.INSTANCE;" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\t" + NL + "\t\t// In all other cases create the usual command" + NL + "\t\treturn super.createRemoveCommand(domain, owner, feature, collection);" + NL + "\t}";
  protected final String TEXT_170 = NL + "\t" + NL + "\t/**" + NL + "\t * *********************************" + NL + "\t * VirSat Specific Code Generation" + NL + "\t * *********************************" + NL + " \t * Override to the createAddCommand Method" + NL + " \t * <!-- begin-user-doc -->" + NL + " \t * <!-- end-user-doc -->" + NL + " \t * @generated" + NL + " \t*/" + NL + "\t@Override" + NL + "\tprotected Command createAddCommand(EditingDomain domain, EObject owner, EStructuralFeature feature,\tCollection<?> collection, int index) {";
  protected final String TEXT_171 = NL + "\t\t// Override functionality with the undoable ADD Command that performs undo by taking out the collection from the containing list" + NL + "\t\t// rather than reducing the index and assuming the last objects on the list have been added by the current command" + NL + "\t\treturn new UndoableAddCommand(domain, owner, feature, collection, index);" + NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t * *********************************" + NL + "\t * VirSat Specific Code Generation" + NL + "\t * *********************************" + NL + " \t * This pipes the command through our RoleManagmentCheckCommand, so we can check directly if a user is allowed to modify" + NL + " \t * <!-- begin-user-doc -->" + NL + " \t * <!-- end-user-doc -->" + NL + " \t * @generated" + NL + " \t*/" + NL + "\t@Override" + NL + "\tpublic Command createCommand(Object object, EditingDomain domain, Class<? extends Command> commandClass, CommandParameter commandParameter) {" + NL + "\t\t" + NL + "\t\t// Set the UserContext either from the SystemUserRegistry or" + NL + "\t\t// from the Domain if it exists" + NL + "\t\tIUserContext userContext = UserRegistry.getInstance();" + NL + "\t\tif (domain instanceof IUserContext) {" + NL + "\t\t\tuserContext = (IUserContext) domain;" + NL + "\t\t}" + NL + "\t\t";
  protected final String TEXT_172 = NL + "\t\t" + NL + "\t\t// For the Repository and StructuralElementInstances we prefer the command for the aplicablefor paradigm" + NL + "\t\t// If the requested command is not valid for the current obejcts an unexecutable command will be handed back instead" + NL + "\t\tDVLMCommandParameterApplicableForCheck commandParameterCheck = new DVLMCommandParameterApplicableForCheck(commandParameter);" + NL + "\t\tif ((commandClass == SetCommand.class)" + NL + "\t\t\t|| (commandClass == CopyCommand.class)" + NL + "\t\t\t|| (commandClass == CreateCopyCommand.class)" + NL + "\t\t\t|| (commandClass == InitializeCopyCommand.class)" + NL + "\t\t\t|| (commandClass == AddCommand.class)" + NL + "\t\t\t|| (commandClass == MoveCommand.class)" + NL + "\t\t\t|| (commandClass == ReplaceCommand.class)" + NL + "\t\t\t|| (commandClass == DragAndDropCommand.class)" + NL + "\t\t\t|| (commandClass == CreateChildCommand.class)) {" + NL + "\t\t\t" + NL + "\t\t\tif (!commandParameterCheck.isValidCommandParameter(commandParameter)) {" + NL + "\t\t\t\treturn new RoleManagementCheckCommand(UnexecutableCommand.INSTANCE, commandParameter, userContext);" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_173 = "\t\t" + NL + "\t\t// We don't want to allow remove or delete operations on concepts that have been introduced into the model" + NL + "\t\tif ((commandClass == RemoveCommand.class) || (commandClass == DeleteCommand.class)) {" + NL + "\t\t\tAtomicBoolean removeConcept = new AtomicBoolean(false);" + NL + "\t\t\tcommandParameter.getCollection().forEach((collectionObject) -> removeConcept.set(removeConcept.get() | collectionObject instanceof Concept));" + NL + "\t\t\tif (removeConcept.get() || (commandParameter.getValue() instanceof Concept)) {" + NL + "\t\t\t\treturn UnexecutableCommand.INSTANCE;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_174 = "\t\t" + NL + "\t\t// Make sure that we do not allow removing items of static arrays." + NL + "\t\t// Only dynamic arrays should change in their size. Static ones are intialized" + NL + "\t\t// once and their size is kept forever." + NL + "\t\tif ((commandClass == RemoveCommand.class) || (commandClass == AddCommand.class)) {" + NL + "\t\t\t" + NL + "\t\t\tEObject eOwner = commandParameter.getEOwner();" + NL + "\t\t\tEObject potentialArrayInstance =  null;" + NL + "\t\t\t" + NL + "\t\t\tif ((eOwner instanceof ComposedPropertyInstance) || (eOwner instanceof ReferencePropertyInstance)) {" + NL + "\t\t\t\tpotentialArrayInstance = eOwner.eContainer();" + NL + "\t\t\t} else {" + NL + "\t\t\t\tpotentialArrayInstance = eOwner;" + NL + "\t\t\t}" + NL + "\t\t\t" + NL + "\t\t\tif (potentialArrayInstance instanceof ArrayInstance) {" + NL + "\t\t\t\tArrayInstance arrayInstance = (ArrayInstance) potentialArrayInstance;" + NL + "\t\t\t\t" + NL + "\t\t\t\tif (arrayInstance.getType() instanceof AProperty) {" + NL + "\t\t\t\t\tAProperty property = (AProperty) arrayInstance.getType();" + NL + "\t\t\t\t\t" + NL + "\t\t\t\t\tif (property.getArrayModifier() instanceof StaticArrayModifier) {" + NL + "\t\t\t\t\t\treturn UnexecutableCommand.INSTANCE;" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_175 = NL + "\t\t// For all other commands get the original one" + NL + "\t\tCommand originalCommand = super.createCommand(object, domain, commandClass, commandParameter);";
  protected final String TEXT_176 = NL + "\t\t// In case we try to set the value we also want to make sure that the override attribute gets set" + NL + "\t\tif (commandClass == SetCommand.class && commandParameter.getEAttribute() == PropertyinstancesPackage.Literals.VALUE_PROPERTY_INSTANCE__VALUE) {" + NL + "\t\t\tCommand setOverrideCommand = SetCommand.create(domain, object, InheritancePackage.Literals.IOVERRIDABLE_INHERITANCE_LINK__OVERRIDE, true);" + NL + "\t\t\tCompoundCommand setValueAndOverrideCommand = new CompoundCommand(\"Set Value and Override\");" + NL + "\t\t\tsetValueAndOverrideCommand.append(setOverrideCommand);" + NL + "\t\t\tsetValueAndOverrideCommand.append(originalCommand);" + NL + "\t\t\treturn setValueAndOverrideCommand;" + NL + "\t\t}";
  protected final String TEXT_177 = NL + "\t\t// In case we try to set the value we also want to make sure that the override attribute gets set" + NL + "\t\tif (commandClass == SetCommand.class && commandParameter.getEAttribute() == PropertyinstancesPackage.Literals.RESOURCE_PROPERTY_INSTANCE__RESOURCE_URI) {" + NL + "\t\t\tCommand setOverrideCommand = SetCommand.create(domain, object, InheritancePackage.Literals.IOVERRIDABLE_INHERITANCE_LINK__OVERRIDE, true);" + NL + "\t\t\tCompoundCommand setValueAndOverrideCommand = new CompoundCommand(\"Set Value and Override\");" + NL + "\t\t\tsetValueAndOverrideCommand.append(setOverrideCommand);" + NL + "\t\t\tsetValueAndOverrideCommand.append(originalCommand);" + NL + "\t\t\treturn setValueAndOverrideCommand;" + NL + "\t\t}";
  protected final String TEXT_178 = NL + "\t\t// In case we try to set the value we also want to make sure that the override attribute gets set" + NL + "\t\tif (commandClass == SetCommand.class && commandParameter.getEReference() == PropertyinstancesPackage.Literals.REFERENCE_PROPERTY_INSTANCE__REFERENCE) {" + NL + "\t\t\tCommand setOverrideCommand = SetCommand.create(domain, object, InheritancePackage.Literals.IOVERRIDABLE_INHERITANCE_LINK__OVERRIDE, true);" + NL + "\t\t\tCompoundCommand setValueAndOverrideCommand = new CompoundCommand(\"Set Value and Override\");" + NL + "\t\t\tsetValueAndOverrideCommand.append(setOverrideCommand);" + NL + "\t\t\tsetValueAndOverrideCommand.append(originalCommand);" + NL + "\t\t\treturn setValueAndOverrideCommand;" + NL + "\t\t}";
  protected final String TEXT_179 = NL + "\t\t// In case we try to set the value we also want to make sure that the override attribute gets set" + NL + "\t\tif (commandClass == SetCommand.class && (commandParameter.getEReference() == PropertyinstancesPackage.Literals.IUNIT_PROPERTY_INSTANCE__UNIT || commandParameter.getEAttribute() == PropertyinstancesPackage.Literals.VALUE_PROPERTY_INSTANCE__VALUE)) {" + NL + "\t\t\tCommand setOverrideCommand = SetCommand.create(domain, object, InheritancePackage.Literals.IOVERRIDABLE_INHERITANCE_LINK__OVERRIDE, true);" + NL + "\t\t\tCompoundCommand setValueAndOverrideCommand = new CompoundCommand(\"Set Value and Override\");" + NL + "\t\t\tsetValueAndOverrideCommand.append(setOverrideCommand);" + NL + "\t\t\tsetValueAndOverrideCommand.append(originalCommand);" + NL + "\t\t\treturn setValueAndOverrideCommand;" + NL + "\t\t}";
  protected final String TEXT_180 = NL + "\t\tif (commandClass == SetCommand.class && commandParameter.getFeature() == GeneralPackage.Literals.INAME__NAME) {" + NL + "\t\t\tCategoryAssignment ca = (CategoryAssignment) object;" + NL + "\t\t\tif (ca.isIsInherited()) {" + NL + "\t\t\t\treturn UnexecutableCommand.INSTANCE;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_181 = NL + "\t\t// A RolemanagementCheckCommand should not necessarily be wrapped into another RoleManagementCheck Command" + NL + "\t\tif (originalCommand instanceof RoleManagementCheckCommand) {" + NL + "\t\t\treturn originalCommand;" + NL + "\t\t} else {" + NL + "\t\t\t// And wrap it into our command checking for the proper access rights" + NL + "\t\t\treturn new RoleManagementCheckCommand(originalCommand, commandParameter, userContext);\t" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_182 = NL + "\t/**" + NL + "\t * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_183 = NL + "\tpublic String getCreateChildText(Object owner, Object feature, Object child, ";
  protected final String TEXT_184 = " selection)" + NL + "\t{" + NL + "\t\tObject childFeature = feature;" + NL + "\t\tObject childObject = child;" + NL;
  protected final String TEXT_185 = NL + "\t\tif (childFeature instanceof ";
  protected final String TEXT_186 = " && ";
  protected final String TEXT_187 = ".isFeatureMap((EStructuralFeature)childFeature))" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_188 = ".Entry entry = (FeatureMap.Entry)childObject;" + NL + "\t\t\tchildFeature = entry.getEStructuralFeature();" + NL + "\t\t\tchildObject = entry.getValue();" + NL + "\t\t}" + NL;
  protected final String TEXT_189 = NL + "\t\tboolean qualify =";
  protected final String TEXT_190 = NL + "\t\t\tchildFeature == ";
  protected final String TEXT_191 = NL + NL + "\t\tif (qualify)" + NL + "\t\t{" + NL + "\t\t\treturn getString" + NL + "\t\t\t\t(\"_UI_CreateChild_text2\",";
  protected final String TEXT_192 = NL + "\t\t\t\t new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });" + NL + "\t\t}" + NL + "\t\treturn super.getCreateChildText(owner, feature, child, selection);" + NL + "\t}" + NL;
  protected final String TEXT_193 = NL + "\t/**" + NL + "\t * Return the resource locator for this item provider's resources." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_194 = " getResourceLocator()" + NL + "\t{";
  protected final String TEXT_195 = NL + "\t\treturn ((";
  protected final String TEXT_196 = ")adapterFactory).getResourceLocator();";
  protected final String TEXT_197 = NL + "\t\treturn ";
  protected final String TEXT_198 = ".INSTANCE;";
  protected final String TEXT_199 = NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * Copyright (c) 2002-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   DLR - customization for Virtual Satellite
 */

    stringBuffer.append(TEXT_1);
    final String MODEL_CLASS_VALUE_PROPERTY_INSTANCE = "ValuePropertyInstance";
    final String MODEL_CLASS_RESOURCE_PROPERTY_INSTANCE = "ResourcePropertyInstance";
    final String MODEL_CLASS_REFERENCE_PROPERTY_INSTANCE = "ReferencePropertyInstance";
    final String MODEL_CLASS_UNIT_VALUE_PROPERTY_INSTANCE = "UnitValuePropertyInstance";
    final String MODEL_CLASS_A_TYPE_INSTANCE = "ATypeInstance";
    final String MODEL_CLASS_I_CATEGORY_ASSIGNMENT_CONTAINER = "ICategoryAssignmentContainer";
    final String MODEL_CLASS_STRUCTURAL_ELEMENT_INSTANCE = "StructuralElementInstance";
    final String MODEL_CLASS_CATEGORY_ASSIGNMENT = "CategoryAssignment";
    final String MODEL_CLASS_STRUCTURAL_ELEMENT = "StructuralElement";
    final String MODEL_CLASS_CATEGORY = "Category";
    final String MODEL_CLASS_REPOSITORY = "Repository";
    final String MODEL_CLASS_ROLEMANAGEMENT = "RoleManagement";
    final String MODEL_CLASS_UNITMANAGEMENT = "UnitManagement";
    final String MODEL_CLASS_ARRAYINSTANCE = "ArrayInstance";
    final String MODEL_CLASS_AUNIT = "AUnit";
    final String MODEL_CLASS_PREFIX = "Prefix";
    stringBuffer.append(TEXT_2);
    GenClass genClass = (GenClass)argument; GenPackage genPackage = genClass.getGenPackage(); GenModel genModel=genPackage.getGenModel();
    stringBuffer.append(TEXT_3);
    stringBuffer.append(TEXT_4);
    {GenBase copyrightHolder = argument instanceof GenBase ? (GenBase)argument : argument instanceof Object[] && ((Object[])argument)[0] instanceof GenBase ? (GenBase)((Object[])argument)[0] : null;
    if (copyrightHolder != null && copyrightHolder.hasCopyright()) {
    stringBuffer.append(TEXT_5);
    stringBuffer.append(copyrightHolder.getCopyright(copyrightHolder.getGenModel().getIndentation(stringBuffer)));
    }}
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genPackage.getProviderPackageName());
    stringBuffer.append(TEXT_7);
    
/*
 * *********************************
 * VirSat Specific Code Generation
 * *********************************
 * Loop over all Interfaces and Extended Classes to find the one defining ATypeInstance
 * Remember it in case it exists. Information will be used later in the code to resolve a custom image for these classes
 */
boolean isInstanceOfValuePropertyInstance = MODEL_CLASS_VALUE_PROPERTY_INSTANCE.equals(genClass.getName());
boolean isInstanceOfResourcePropertyInstance = MODEL_CLASS_RESOURCE_PROPERTY_INSTANCE.equals(genClass.getName());
boolean isInstanceOfReferencePropertyInstance = MODEL_CLASS_REFERENCE_PROPERTY_INSTANCE.equals(genClass.getName());
boolean isInstanceOfUnitValuePropertyInstance = MODEL_CLASS_UNIT_VALUE_PROPERTY_INSTANCE.equals(genClass.getName());
boolean isInstanceOfATypeInstance = genClass.getAllBaseGenClasses().stream().anyMatch((GenClass predicate) -> MODEL_CLASS_A_TYPE_INSTANCE.equals(predicate.getInterfaceName()));
boolean isInstanceOfICategoryAssignmentContainer = genClass.getAllBaseGenClasses().stream().anyMatch((GenClass predicate) -> MODEL_CLASS_I_CATEGORY_ASSIGNMENT_CONTAINER.equals(predicate.getInterfaceName()));
boolean isInstanceOfStructuralElementInstance = MODEL_CLASS_STRUCTURAL_ELEMENT_INSTANCE.equals(genClass.getName());
boolean isInstanceOfStructuralElement = MODEL_CLASS_STRUCTURAL_ELEMENT.equals(genClass.getName());
boolean isInstanceOfCategory = MODEL_CLASS_CATEGORY.equals(genClass.getName());
boolean isInstanceOfCategoryAssignment = MODEL_CLASS_CATEGORY_ASSIGNMENT.equals(genClass.getName());
boolean isInstanceOfRepository = MODEL_CLASS_REPOSITORY.equals(genClass.getName());
boolean isInstanceOfRoleManagement = MODEL_CLASS_ROLEMANAGEMENT.equals(genClass.getName());
boolean isInstanceOfUnitManagement = MODEL_CLASS_UNITMANAGEMENT.equals(genClass.getName());
boolean isInstanceOfArrayInstance = MODEL_CLASS_ARRAYINSTANCE.equals(genClass.getName());
boolean isInstanceOfAUnit = genClass.getAllBaseGenClasses().stream().anyMatch((GenClass predicate) -> MODEL_CLASS_AUNIT.equals(predicate.getInterfaceName()));
boolean isInstanceOfPrefix = MODEL_CLASS_PREFIX.equals(genClass.getName());

//System.out.println("ClassName: " + String.format("%25s", genClass.getName()) + "  ATypeInstance: " + isInstanceOfATypeInstance + "  Repository: " + isInstanceOfRepository);

    genModel.addImport("org.eclipse.emf.common.notify.AdapterFactory");
    genModel.addImport("org.eclipse.emf.common.notify.Notification");
    genModel.addImport("org.eclipse.emf.common.command.Command");
    genModel.addImport("org.eclipse.emf.edit.command.CommandParameter");
    genModel.addImport("org.eclipse.emf.edit.domain.EditingDomain");
    genModel.addImport("de.dlr.sc.virsat.model.dvlm.roles.RoleManagementCheckCommand");
    genModel.addImport("de.dlr.sc.virsat.model.dvlm.roles.IUserContext");
    genModel.addImport("de.dlr.sc.virsat.model.dvlm.roles.UserRegistry");
    stringBuffer.append(TEXT_3);
    String _List = genModel.getImportedName(genModel.useGenerics() ? "java.util.List<org.eclipse.emf.edit.provider.IItemPropertyDescriptor>" : "java.util.List");
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(genClass.getProviderClassName());
    stringBuffer.append(TEXT_10);
    if (genClass.getProviderImplementsClassNames().isEmpty()) {
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genClass.getProviderBaseClassName() != null ? genClass.getProviderBaseClassName() : genModel.getImportedName("org.eclipse.emf.edit.provider.ItemProviderAdapter"));
    }
    if (!genClass.getProviderImplementsClassNames().isEmpty()) {
    stringBuffer.append(TEXT_12);
    stringBuffer.append(genClass.getProviderBaseClassName() != null ? genClass.getProviderBaseClassName() : genModel.getImportedName("org.eclipse.emf.edit.provider.ItemProviderAdapter"));
    stringBuffer.append(TEXT_13);
    for (Iterator<String> i = genClass.getProviderImplementsClassNames().iterator(); i.hasNext(); ) {
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genModel.getImportedName(i.next()));
    if (i.hasNext()){
    stringBuffer.append(TEXT_15);
    }
    }
    }
    stringBuffer.append(TEXT_16);
    if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_3);
    }
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genClass.getProviderClassName());
    stringBuffer.append(TEXT_21);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_22);
    }
    stringBuffer.append(TEXT_23);
    stringBuffer.append(_List);
    stringBuffer.append(TEXT_24);
    for (GenFeature genFeature : genClass.getPropertyFeatures()) { 
    stringBuffer.append(TEXT_25);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_26);
    }
    stringBuffer.append(TEXT_27);
    for (GenFeature genFeature : genClass.getPropertyFeatures()) { 
    stringBuffer.append(TEXT_28);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_29);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_30);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.ComposeableAdapterFactory"));
    stringBuffer.append(TEXT_32);
    stringBuffer.append(genFeature.getGenClass().getName());
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genModel.getNonNLS());
    if (genFeature.getPropertyDescription() == null || genFeature.getPropertyDescription().length() == 0) {
    stringBuffer.append(TEXT_35);
    stringBuffer.append(genFeature.getGenClass().getName());
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genFeature.getGenClass().getName());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(genModel.getNonNLS(3));
    } else {
    stringBuffer.append(TEXT_38);
    stringBuffer.append(genFeature.getGenClass().getName());
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genModel.getNonNLS());
    }
    stringBuffer.append(TEXT_40);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genFeature.getProperty() == GenPropertyKind.EDITABLE_LITERAL ? "true" : "false");
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genFeature.isPropertyMultiLine() ? "true" : "false");
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genFeature.isPropertySortChoices() ? "true" : "false");
    stringBuffer.append(TEXT_15);
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_42);
    } else {
    stringBuffer.append(TEXT_40);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.ItemPropertyDescriptor"));
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genFeature.getPropertyImageName());
    stringBuffer.append(TEXT_15);
    }
    if (genFeature.getPropertyCategory() == null || genFeature.getPropertyCategory().length() == 0) {
    stringBuffer.append(TEXT_42);
    } else {
    stringBuffer.append(TEXT_44);
    stringBuffer.append(genModel.getPropertyCategoryKey(genFeature.getPropertyCategory()));
    stringBuffer.append(TEXT_45);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.getPropertyFilterFlags().isEmpty()) {
    stringBuffer.append(TEXT_46);
    } else {
    stringBuffer.append(TEXT_47);
    for (Iterator<String> j = genFeature.getPropertyFilterFlags().iterator(); j.hasNext();) { String filterFlag = j.next();
    if (filterFlag != null && filterFlag.length() > 0) {
    stringBuffer.append(TEXT_48);
    stringBuffer.append(filterFlag);
    stringBuffer.append(TEXT_49);
    if (j.hasNext()) {
    stringBuffer.append(TEXT_15);
    }
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    stringBuffer.append(TEXT_50);
    }
    //ItemProvider/addPropertyDescriptor.override.javajetinc
    stringBuffer.append(TEXT_51);
    }
    if (!genClass.getChildrenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_52);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_22);
    }
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genModel.getImportedName(genModel.useGenerics() ? "java.util.Collection<? extends org.eclipse.emf.ecore.EStructuralFeature>" : "java.util.Collection"));
    stringBuffer.append(TEXT_53);
    for (GenFeature genFeature : genClass.getChildrenFeatures()) { 
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_55);
    }
    stringBuffer.append(TEXT_56);
    if (!genClass.getChildrenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_57);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_22);
    }
    stringBuffer.append(TEXT_58);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_59);
    }
    }
    if (genClass.needsHasChildrenMethodOverride()) {
    stringBuffer.append(TEXT_57);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_22);
    }
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genModel.isOptimizedHasChildren());
    stringBuffer.append(TEXT_61);
    }
    if (genClass.isImage()) {
    stringBuffer.append(TEXT_62);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_63);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_22);
    }
    stringBuffer.append(TEXT_64);
    if (isInstanceOfATypeInstance | isInstanceOfStructuralElementInstance | isInstanceOfStructuralElement | isInstanceOfCategory) {
    genModel.getImportedName("de.dlr.sc.virsat.model.dvlm.provider.DVLMEditPlugin");
    genModel.getImportedName("java.net.URL");
    genModel.getImportedName("de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance");
    genModel.getImportedName("de.dlr.sc.virsat.model.dvlm.general.IQualifiedName");
    genModel.getImportedName("de.dlr.sc.virsat.model.dvlm.structural.StructuralElement");
    genModel.getImportedName("de.dlr.sc.virsat.model.dvlm.categories.Category");
    genModel.getImportedName("de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance");
    stringBuffer.append(TEXT_65);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_66);
    } else {
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_69);
    }
    }
    if (genModel.getRuntimeVersion().getValue() >= GenRuntimeVersion.EMF26_VALUE && !genModel.isCreationIcons()) {
    stringBuffer.append(TEXT_57);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_22);
    }
    stringBuffer.append(TEXT_70);
    }
    stringBuffer.append(TEXT_71);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_22);
    }
    stringBuffer.append(TEXT_72);
    stringBuffer.append(TEXT_3);
    if (isInstanceOfRepository | isInstanceOfRoleManagement | isInstanceOfUnitManagement) {
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_74);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    if (isInstanceOfAUnit) {
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_77);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genClass.getLabelFeature().getGetAccessor());
    stringBuffer.append(TEXT_79);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_10);
    } else {
    if (isInstanceOfPrefix) {
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_77);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genClass.getLabelFeature().getGetAccessor());
    stringBuffer.append(TEXT_79);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_10);
    } else {
    if (genModel.isStyleProviders()) {
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString"));
    stringBuffer.append(TEXT_81);
    } else {
    if (genClass.isMapEntry()) {
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genClass.getImportedInterfaceName());
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_83);
    }
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genClass.getImportedInterfaceName());
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_83);
    }
    stringBuffer.append(TEXT_85);
    if (!genClass.getMapEntryKeyFeature().isPropertyMultiLine() && !genClass.getMapEntryValueFeature().isPropertyMultiLine()) {
    stringBuffer.append(TEXT_86);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    } else {
    if (genClass.getMapEntryKeyFeature().isPropertyMultiLine()) {
    stringBuffer.append(TEXT_89);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_92);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genClass.getMapEntryValueFeature().isPropertyMultiLine()) {
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_94);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_95);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genModel.getNonNLS());
    }
    stringBuffer.append(TEXT_96);
    stringBuffer.append(genModel.getNonNLS());
    }
    } else if (genClass.getLabelFeature() != null) { GenFeature labelFeature = genClass.getLabelFeature();
    if (labelFeature.isPrimitiveType() && !labelFeature.getGenClass().isDynamic() && !labelFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_97);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genClass.getLabelFeature().getGetAccessor());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    } else {
    if (labelFeature.isStringType() && !labelFeature.getGenClass().isDynamic() && !labelFeature.isSuppressedGetVisibility()) {
    if (labelFeature.isPropertyMultiLine()) {
    stringBuffer.append(TEXT_100);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_78);
    stringBuffer.append(labelFeature.getGetAccessor());
    stringBuffer.append(TEXT_101);
    } else {
    stringBuffer.append(TEXT_102);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_78);
    stringBuffer.append(labelFeature.getGetAccessor());
    stringBuffer.append(TEXT_99);
    }
    } else {
    if (labelFeature.isSuppressedGetVisibility() || labelFeature.getGenClass().isDynamic()) {
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genModel.getImportedName("java.lang.Object"));
    stringBuffer.append(TEXT_103);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_104);
    stringBuffer.append(labelFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_55);
    } else {
    stringBuffer.append(TEXT_82);
    stringBuffer.append(labelFeature.getRawImportedType());
    stringBuffer.append(TEXT_103);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_78);
    stringBuffer.append(labelFeature.getGetAccessor());
    stringBuffer.append(TEXT_99);
    }
    stringBuffer.append(TEXT_105);
    }
    stringBuffer.append(TEXT_106);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_108);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_109);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    }
    } else {
    stringBuffer.append(TEXT_110);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_74);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    }
    }
    }
    //ItemProvider/getText.override.javajetinc
    stringBuffer.append(TEXT_111);
    if (genModel.isStyleProviders()) {
    stringBuffer.append(TEXT_112);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_22);
    }
    stringBuffer.append(TEXT_113);
    if (genClass.isMapEntry()) {
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genClass.getImportedInterfaceName());
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_83);
    }
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genClass.getImportedInterfaceName());
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_83);
    }
    stringBuffer.append(TEXT_85);
    if (!genClass.getMapEntryKeyFeature().isPropertyMultiLine() && !genClass.getMapEntryValueFeature().isPropertyMultiLine()) {
    stringBuffer.append(TEXT_114);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString"));
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString$Style"));
    stringBuffer.append(TEXT_117);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_94);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    } else {
    if (genClass.getMapEntryKeyFeature().isPropertyMultiLine()) {
    stringBuffer.append(TEXT_118);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_119);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_92);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genClass.getMapEntryValueFeature().isPropertyMultiLine()) {
    stringBuffer.append(TEXT_120);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_94);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_121);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genModel.getNonNLS());
    }
    stringBuffer.append(TEXT_114);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString"));
    stringBuffer.append(TEXT_122);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString$Style"));
    stringBuffer.append(TEXT_123);
    stringBuffer.append(genModel.getNonNLS());
    }
    } else if (genClass.getLabelFeature() != null) { GenFeature labelFeature = genClass.getLabelFeature();
    if (labelFeature.isPrimitiveType() && !labelFeature.getGenClass().isDynamic() && !labelFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString"));
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_126);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString$Style"));
    stringBuffer.append(TEXT_127);
    stringBuffer.append(labelFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_128);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genClass.getLabelFeature().getGetAccessor());
    stringBuffer.append(TEXT_129);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    } else {
    if (labelFeature.isStringType() && !labelFeature.getGenClass().isDynamic() && !labelFeature.isSuppressedGetVisibility()) {
    if (labelFeature.isPropertyMultiLine()) {
    stringBuffer.append(TEXT_130);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_78);
    stringBuffer.append(labelFeature.getGetAccessor());
    stringBuffer.append(TEXT_101);
    } else {
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_78);
    stringBuffer.append(labelFeature.getGetAccessor());
    stringBuffer.append(TEXT_99);
    }
    } else {
    if (labelFeature.isSuppressedGetVisibility() || labelFeature.getGenClass().isDynamic()) {
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genModel.getImportedName("java.lang.Object"));
    stringBuffer.append(TEXT_103);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_104);
    stringBuffer.append(labelFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_55);
    } else {
    stringBuffer.append(TEXT_14);
    stringBuffer.append(labelFeature.getRawImportedType());
    stringBuffer.append(TEXT_103);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_78);
    stringBuffer.append(labelFeature.getGetAccessor());
    stringBuffer.append(TEXT_99);
    }
    stringBuffer.append(TEXT_132);
    }
    stringBuffer.append(TEXT_133);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString"));
    stringBuffer.append(TEXT_134);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString"));
    stringBuffer.append(TEXT_135);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_126);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString$Style"));
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_137);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_126);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString$Style"));
    stringBuffer.append(TEXT_138);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_139);
    }
    } else {
    stringBuffer.append(TEXT_114);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString"));
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_140);
    stringBuffer.append(genModel.getNonNLS());
    }
    //ItemProvider/getStyledText.override.javajetinc
    stringBuffer.append(TEXT_141);
    }
    stringBuffer.append(TEXT_142);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_22);
    }
    stringBuffer.append(TEXT_143);
    if (!genClass.getLabelNotifyFeatures().isEmpty() || !genClass.getContentNotifyFeatures().isEmpty() || !genClass.getLabelAndContentNotifyFeatures().isEmpty()) {
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_145);
    if (!genClass.getLabelNotifyFeatures().isEmpty()) {
    for (GenFeature genFeature : genClass.getLabelNotifyFeatures()) { 
    stringBuffer.append(TEXT_146);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_147);
    }
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.ViewerNotification"));
    stringBuffer.append(TEXT_149);
    }
    if (!genClass.getContentNotifyFeatures().isEmpty()) {
    for (GenFeature genFeature : genClass.getContentNotifyFeatures()) { 
    stringBuffer.append(TEXT_146);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_147);
    }
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.ViewerNotification"));
    stringBuffer.append(TEXT_150);
    }
    if (!genClass.getLabelAndContentNotifyFeatures().isEmpty()) {
    for (GenFeature genFeature : genClass.getLabelAndContentNotifyFeatures()) { 
    stringBuffer.append(TEXT_146);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_147);
    }
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.ViewerNotification"));
    stringBuffer.append(TEXT_151);
    }
    stringBuffer.append(TEXT_152);
    }
    stringBuffer.append(TEXT_153);
    if (genModel.isCreationCommands()) {
    stringBuffer.append(TEXT_154);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_22);
    }
    stringBuffer.append(TEXT_155);
    stringBuffer.append(genModel.getImportedName(genModel.useGenerics() ? "java.util.Collection<java.lang.Object>" : "java.util.Collection"));
    stringBuffer.append(TEXT_156);
    for (GenClass.ChildCreationData childCreationData : genClass.getChildCreationData()) { GenFeature createFeature = childCreationData.createFeature; GenFeature delegatedFeature = childCreationData.delegatedFeature; GenClassifier createClassifier = childCreationData.createClassifier;
    if (createFeature.isFeatureMapType()) {
    if (delegatedFeature.isReferenceType()) { GenClass createClass = (GenClass)createClassifier;
    stringBuffer.append(TEXT_157);
    stringBuffer.append(createFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMapUtil"));
    stringBuffer.append(TEXT_158);
    stringBuffer.append(delegatedFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_15);
    if (createClass.isMapEntry()) { 
    stringBuffer.append(TEXT_159);
    stringBuffer.append(createClass.getGenPackage().getQualifiedEFactoryInstanceAccessor());
    stringBuffer.append(TEXT_160);
    stringBuffer.append(createClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_161);
    } else {
    stringBuffer.append(TEXT_159);
    stringBuffer.append(createClass.getGenPackage().getQualifiedFactoryInstanceAccessor());
    stringBuffer.append(TEXT_162);
    stringBuffer.append(createClass.getName());
    stringBuffer.append(TEXT_163);
    }
    //ItemProvider/newChildDescriptorsReferenceDelegatedFeature.override.javajetinc
    } else { GenDataType createDataType = (GenDataType)createClassifier;
    stringBuffer.append(TEXT_157);
    stringBuffer.append(createFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMapUtil"));
    stringBuffer.append(TEXT_158);
    stringBuffer.append(delegatedFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_15);
    if (delegatedFeature.isEnumBasedType()) {
    stringBuffer.append(TEXT_159);
    stringBuffer.append(delegatedFeature.getTypeGenEnum().getStaticValue(delegatedFeature.getEcoreFeature().getDefaultValueLiteral()));
    stringBuffer.append(TEXT_164);
    } else if (delegatedFeature.isStringBasedType()) {
    stringBuffer.append(TEXT_159);
    stringBuffer.append(delegatedFeature.getCreateChildValueLiteral());
    stringBuffer.append(TEXT_164);
    stringBuffer.append(genModel.getNonNLS());
    } else { String literal = delegatedFeature.getCreateChildValueLiteral();
    stringBuffer.append(TEXT_159);
    stringBuffer.append(createDataType.getGenPackage().getQualifiedEFactoryInstanceAccessor());
    stringBuffer.append(TEXT_165);
    stringBuffer.append(createDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_166);
    stringBuffer.append(literal);
    stringBuffer.append(TEXT_161);
    if (literal != null) {
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_167);
    }
    }
    //ItemProvider/newChildDescriptorsAttributeDelegatedFeature.override.javajetinc
    }
    } else if (createFeature.isReferenceType()) { GenClass createClass = (GenClass)createClassifier;
    stringBuffer.append(TEXT_157);
    stringBuffer.append(createFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_15);
    if (createClass.isMapEntry()) { 
    stringBuffer.append(TEXT_40);
    stringBuffer.append(createClass.getGenPackage().getQualifiedEFactoryInstanceAccessor());
    stringBuffer.append(TEXT_160);
    stringBuffer.append(createClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_164);
    } else {
    stringBuffer.append(TEXT_40);
    stringBuffer.append(createClass.getGenPackage().getQualifiedFactoryInstanceAccessor());
    stringBuffer.append(TEXT_162);
    stringBuffer.append(createClass.getName());
    stringBuffer.append(TEXT_129);
    }
    //ItemProvider/newChildDescriptorsReferenceFeature.override.javajetinc 
    } else { GenDataType createDataType = (GenDataType)createClassifier;
    stringBuffer.append(TEXT_157);
    stringBuffer.append(createFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_15);
    if (createFeature.isEnumBasedType()) {
    stringBuffer.append(TEXT_40);
    stringBuffer.append(createFeature.getTypeGenEnum().getStaticValue(createFeature.getEcoreFeature().getDefaultValueLiteral()));
    stringBuffer.append(TEXT_168);
    } else if (createFeature.isStringBasedType()) {
    stringBuffer.append(TEXT_40);
    stringBuffer.append(createFeature.getCreateChildValueLiteral());
    stringBuffer.append(TEXT_168);
    stringBuffer.append(genModel.getNonNLS());
    } else { String literal = createFeature.getCreateChildValueLiteral();
    stringBuffer.append(TEXT_40);
    stringBuffer.append(createDataType.getGenPackage().getQualifiedEFactoryInstanceAccessor());
    stringBuffer.append(TEXT_165);
    stringBuffer.append(createDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_166);
    stringBuffer.append(literal);
    stringBuffer.append(TEXT_164);
    if (literal != null) {
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_167);
    }
    }
    //ItemProvider/newChildDescriptorsAttributeFeature.override.javajetinc
    }
    }
    stringBuffer.append(TEXT_69);
     
    // Here we override the command for removing ATypeinstances
    // This should only happen in the cases where no super TIs are present
    // at the object. In all otehr cases the following method should either
    // not be generated ot refer to the create Comamnd of the super class
    if (isInstanceOfICategoryAssignmentContainer) {
    genModel.getImportedName("org.eclipse.emf.ecore.EObject");
    genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature");
    genModel.getImportedName("org.eclipse.emf.common.command.UnexecutableCommand");	
    stringBuffer.append(TEXT_169);
     } 
    stringBuffer.append(TEXT_170);
    	genModel.getImportedName("de.dlr.sc.virsat.model.dvlm.command.UndoableAddCommand");
        genModel.getImportedName("org.eclipse.emf.ecore.EObject");
        genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature");
        genModel.getImportedName("java.util.Collection");
        genModel.getImportedName("org.eclipse.emf.edit.domain.EditingDomain");
    stringBuffer.append(TEXT_171);
    if (isInstanceOfRepository || isInstanceOfStructuralElementInstance) {
        genModel.getImportedName("de.dlr.sc.virsat.model.dvlm.util.DVLMCommandParameterApplicableForCheck");
        genModel.getImportedName("org.eclipse.emf.edit.command.AddCommand");
        genModel.getImportedName("org.eclipse.emf.edit.command.CommandParameter");
        genModel.getImportedName("org.eclipse.emf.edit.command.CopyCommand");
        genModel.getImportedName("org.eclipse.emf.edit.command.CreateChildCommand");
        genModel.getImportedName("org.eclipse.emf.edit.command.CreateCopyCommand");
        genModel.getImportedName("org.eclipse.emf.edit.command.DragAndDropCommand");
        genModel.getImportedName("org.eclipse.emf.edit.command.InitializeCopyCommand");
        genModel.getImportedName("org.eclipse.emf.edit.command.MoveCommand");
        genModel.getImportedName("org.eclipse.emf.edit.command.ReplaceCommand");
        genModel.getImportedName("org.eclipse.emf.edit.command.SetCommand");
        genModel.getImportedName("org.eclipse.emf.common.command.UnexecutableCommand");
    stringBuffer.append(TEXT_172);
    }
        if (isInstanceOfRepository) {
        // In case we create some commands on the repository we want that 
        // they will be forwarded into an unexecutable command. E.G we expect such
        // a behaviour for removing already active concepts. Such a command should
        // not be executable.		
        genModel.getImportedName("de.dlr.sc.virsat.model.dvlm.concepts.Concept");
        genModel.getImportedName("java.util.concurrent.atomic.AtomicBoolean");
        genModel.getImportedName("org.eclipse.emf.edit.command.DeleteCommand");
        genModel.getImportedName("org.eclipse.emf.edit.command.RemoveCommand");
        genModel.getImportedName("org.eclipse.emf.common.command.UnexecutableCommand");
    stringBuffer.append(TEXT_173);
    } else if (isInstanceOfArrayInstance) {
        // Arrays are specific as well. Only dynamic arrays should allow to remove
        // or delete items of them. Static arrays should never be changed
        // once they are initialized.
        genModel.getImportedName("org.eclipse.emf.ecore.EObject");
        genModel.getImportedName("org.eclipse.emf.edit.command.RemoveCommand");
        genModel.getImportedName("org.eclipse.emf.edit.command.AddCommand");
        genModel.getImportedName("org.eclipse.emf.common.command.UnexecutableCommand");
        genModel.getImportedName("de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance");
        genModel.getImportedName("de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance");
        genModel.getImportedName("de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.command.ArrayInstance");
        genModel.getImportedName("de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty");
        genModel.getImportedName("de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StaticArrayModifier"); 
    stringBuffer.append(TEXT_174);
    }
    stringBuffer.append(TEXT_175);
    if (isInstanceOfValuePropertyInstance) {
        // Incase we are accessing the set value method of a derived data item
        // we want to have the override status being set, otherwise, the inehritance builder
        // will directly change back the changes that ahve been set with the setvalue method
        genModel.getImportedName("org.eclipse.emf.common.command.CompoundCommand");
        genModel.getImportedName("org.eclipse.emf.edit.command.SetCommand"); 
    stringBuffer.append(TEXT_176);
    }
    if (isInstanceOfResourcePropertyInstance) {
        // Incase we are accessing the set value method of a derived data item
        // we want to have the override status being set, otherwise, the inehritance builder
        // will directly change back the changes that ahve been set with the setvalue method
        genModel.getImportedName("org.eclipse.emf.common.command.CompoundCommand");
        genModel.getImportedName("org.eclipse.emf.edit.command.SetCommand"); 
    stringBuffer.append(TEXT_177);
    }
    if (isInstanceOfReferencePropertyInstance) {
        // Incase we are accessing the set value method of a derived data item
        // we want to have the override status being set, otherwise, the inehritance builder
        // will directly change back the changes that ahve been set with the setvalue method
        genModel.getImportedName("org.eclipse.emf.common.command.CompoundCommand");
        genModel.getImportedName("org.eclipse.emf.edit.command.SetCommand"); 
    stringBuffer.append(TEXT_178);
    }
    if (isInstanceOfUnitValuePropertyInstance) {
        // Incase we are accessing the set value method of a derived data item
        // we want to have the override status being set, otherwise, the inehritance builder
        // will directly change back the changes that have been set with the setvalue method
        genModel.getImportedName("org.eclipse.emf.common.command.CompoundCommand");
        genModel.getImportedName("org.eclipse.emf.edit.command.SetCommand"); 
    stringBuffer.append(TEXT_179);
    }
    if (isInstanceOfCategoryAssignment) {
        // Incase we are accessing the set value method of a category assignment
        // We need to check if it has been inherited and if that is the case
        // We disallow changing the name via the set method
        genModel.getImportedName("org.eclipse.emf.edit.command.SetCommand"); 
        genModel.getImportedName("org.eclipse.emf.common.command.UnexecutableCommand"); 
    stringBuffer.append(TEXT_180);
    }
    stringBuffer.append(TEXT_181);
    if (!genClass.getSharedClassCreateChildFeatures().isEmpty()) {
    stringBuffer.append(TEXT_182);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_22);
    }
    stringBuffer.append(TEXT_183);
    stringBuffer.append(genModel.getImportedName(genModel.useGenerics() ? "java.util.Collection<?>" : "java.util.Collection"));
    stringBuffer.append(TEXT_184);
    if (genClass.hasFeatureMapCreateChildFeatures()) {
    stringBuffer.append(TEXT_185);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_186);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMapUtil"));
    stringBuffer.append(TEXT_187);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_188);
    }
    stringBuffer.append(TEXT_189);
    for (Iterator<GenFeature> i = genClass.getSharedClassCreateChildFeatures().iterator(); i.hasNext();) { GenFeature createFeature = i.next();
    stringBuffer.append(TEXT_190);
    stringBuffer.append(createFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(i.hasNext() ? " ||" : ";");
    }
    stringBuffer.append(TEXT_191);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_192);
    }
    }
    if (genClass.getProviderExtendsGenClass() == null || genClass.getProviderExtendsGenClass().getGenPackage() != genPackage && (!genPackage.isExtensibleProviderFactory() || genClass.getProviderExtendsGenClass().getGenPackage().isExtensibleProviderFactory() != genPackage.isExtensibleProviderFactory())) {
    stringBuffer.append(TEXT_193);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_22);
    }
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.ResourceLocator"));
    stringBuffer.append(TEXT_194);
    if (genPackage.isExtensibleProviderFactory()) {
    stringBuffer.append(TEXT_195);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.IChildCreationExtender"));
    stringBuffer.append(TEXT_196);
    } else {
    stringBuffer.append(TEXT_197);
    stringBuffer.append(genPackage.getImportedEditPluginClassName());
    stringBuffer.append(TEXT_198);
    }
    stringBuffer.append(TEXT_51);
    }
    stringBuffer.append(TEXT_199);
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_3);
    return stringBuffer.toString();
  }
}
