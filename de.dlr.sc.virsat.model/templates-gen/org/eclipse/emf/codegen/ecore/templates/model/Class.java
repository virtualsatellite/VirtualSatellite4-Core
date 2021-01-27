package org.eclipse.emf.codegen.ecore.templates.model;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.emf.codegen.util.CodeGenUtil;

public class Class
{
  protected static String nl;
  public static synchronized Class create(String lineSeparator)
  {
    nl = lineSeparator;
    Class result = new Class();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**";
  protected final String TEXT_3 = NL + " * ";
  protected final String TEXT_4 = NL + " */";
  protected final String TEXT_5 = NL + "package ";
  protected final String TEXT_6 = ";";
  protected final String TEXT_7 = NL;
  protected final String TEXT_8 = NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * A representation of the model object '<em><b>";
  protected final String TEXT_9 = "</b></em>'." + NL + " * <!-- end-user-doc -->";
  protected final String TEXT_10 = NL + " *" + NL + " * <!-- begin-model-doc -->" + NL + " * ";
  protected final String TEXT_11 = NL + " * <!-- end-model-doc -->";
  protected final String TEXT_12 = NL + " *";
  protected final String TEXT_13 = NL + " * <p>" + NL + " * The following features are supported:" + NL + " * </p>" + NL + " * <ul>";
  protected final String TEXT_14 = NL + " *   <li>{@link ";
  protected final String TEXT_15 = "#";
  protected final String TEXT_16 = " <em>";
  protected final String TEXT_17 = "</em>}</li>";
  protected final String TEXT_18 = NL + " * </ul>";
  protected final String TEXT_19 = NL + " * @see ";
  protected final String TEXT_20 = "#get";
  protected final String TEXT_21 = "()";
  protected final String TEXT_22 = NL + " * @model ";
  protected final String TEXT_23 = NL + " *        ";
  protected final String TEXT_24 = NL + " * @model";
  protected final String TEXT_25 = NL + " * @extends ";
  protected final String TEXT_26 = NL + " * @generated" + NL + " */";
  protected final String TEXT_27 = NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * An implementation of the model object '<em><b>";
  protected final String TEXT_28 = NL + " * <p>" + NL + " * The following features are implemented:" + NL + " * </p>" + NL + " * <ul>";
  protected final String TEXT_29 = NL + " *" + NL + " * @generated" + NL + " */";
  protected final String TEXT_30 = NL + "public";
  protected final String TEXT_31 = " abstract";
  protected final String TEXT_32 = " class ";
  protected final String TEXT_33 = NL + "public interface ";
  protected final String TEXT_34 = NL + "{";
  protected final String TEXT_35 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_36 = " copyright = ";
  protected final String TEXT_37 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_38 = " mofDriverNumber = \"";
  protected final String TEXT_39 = "\";";
  protected final String TEXT_40 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final long serialVersionUID = 1L;" + NL;
  protected final String TEXT_41 = NL + "\t/**" + NL + "\t * An array of objects representing the values of non-primitive features." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_42 = NL + "\t@";
  protected final String TEXT_43 = NL + "\tprotected Object[] ";
  protected final String TEXT_44 = ";" + NL;
  protected final String TEXT_45 = NL + "\t/**" + NL + "\t * A bit field representing the indices of non-primitive feature values." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_46 = NL + "\tprotected int ";
  protected final String TEXT_47 = NL + "\t/**" + NL + "\t * A set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_48 = " = 0;" + NL;
  protected final String TEXT_49 = NL + "\t/**" + NL + "\t * The empty value for the '{@link #";
  protected final String TEXT_50 = "() <em>";
  protected final String TEXT_51 = "</em>}' array accessor." + NL + "\t * This is specialized for the more specific element type known in this context." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_52 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_53 = NL + "\t@SuppressWarnings(\"rawtypes\")";
  protected final String TEXT_54 = NL + "\tprotected static final ";
  protected final String TEXT_55 = "[] ";
  protected final String TEXT_56 = "_EEMPTY_ARRAY = new ";
  protected final String TEXT_57 = " [0]";
  protected final String TEXT_58 = NL + "\t/**" + NL + "\t * The cached setting delegate for the '{@link #";
  protected final String TEXT_59 = "</em>}' ";
  protected final String TEXT_60 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_61 = NL + "\tprotected ";
  protected final String TEXT_62 = ".Internal.SettingDelegate ";
  protected final String TEXT_63 = "__ESETTING_DELEGATE = ((";
  protected final String TEXT_64 = ".Internal)";
  protected final String TEXT_65 = ").getSettingDelegate();" + NL;
  protected final String TEXT_66 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_67 = " ";
  protected final String TEXT_68 = "</em>}' array accessor." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_69 = NL + "\t/**" + NL + "\t * The default value of the '{@link #";
  protected final String TEXT_70 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_71 = "; // TODO The default value literal \"";
  protected final String TEXT_72 = "\" is not valid.";
  protected final String TEXT_73 = " = ";
  protected final String TEXT_74 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_75 = NL + "\t/**" + NL + "\t * The offset of the flags representing the value of the '{@link #";
  protected final String TEXT_76 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_77 = "_EFLAG_OFFSET = ";
  protected final String TEXT_78 = ";" + NL + "" + NL + "\t/**" + NL + "\t * The flags representing the default value of the '{@link #";
  protected final String TEXT_79 = "_EFLAG_DEFAULT = ";
  protected final String TEXT_80 = ".ordinal()";
  protected final String TEXT_81 = ".VALUES.indexOf(";
  protected final String TEXT_82 = ")";
  protected final String TEXT_83 = " << ";
  protected final String TEXT_84 = "_EFLAG_OFFSET;" + NL + "" + NL + "\t/**" + NL + "\t * The array of enumeration values for '{@link ";
  protected final String TEXT_85 = "}'" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprivate static final ";
  protected final String TEXT_86 = "_EFLAG_VALUES = ";
  protected final String TEXT_87 = ".values()";
  protected final String TEXT_88 = "(";
  protected final String TEXT_89 = "[])";
  protected final String TEXT_90 = ".VALUES.toArray(new ";
  protected final String TEXT_91 = "[";
  protected final String TEXT_92 = ".VALUES.size()])";
  protected final String TEXT_93 = NL + "\t/**" + NL + "\t * The flag";
  protected final String TEXT_94 = " representing the value of the '{@link #";
  protected final String TEXT_95 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_96 = "_EFLAG = ";
  protected final String TEXT_97 = "_EFLAG_OFFSET";
  protected final String TEXT_98 = NL + "\t/**" + NL + "\t * The flag representing whether the ";
  protected final String TEXT_99 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_100 = "_ESETFLAG = 1 << ";
  protected final String TEXT_101 = NL + "\t/**" + NL + "\t * This is true if the ";
  protected final String TEXT_102 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_103 = NL + "\tprotected boolean ";
  protected final String TEXT_104 = "ESet;" + NL;
  protected final String TEXT_105 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int ";
  protected final String TEXT_106 = ".getFeatureID(";
  protected final String TEXT_107 = ") - ";
  protected final String TEXT_108 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int \"EOPERATION_OFFSET_CORRECTION\" = ";
  protected final String TEXT_109 = ".getOperationID(";
  protected final String TEXT_110 = NL + "\t/**" + NL + "\t * *********************************" + NL + "\t * VirSat Specific Code Generation" + NL + "\t * *********************************" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_111 = "public";
  protected final String TEXT_112 = "protected";
  protected final String TEXT_113 = "()" + NL + "\t{" + NL + "\t\tsuper();" + NL + "\t\t";
  protected final String TEXT_114 = "this.uuid = new de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid(); ";
  protected final String TEXT_115 = NL + "\t\t";
  protected final String TEXT_116 = " |= ";
  protected final String TEXT_117 = "_EFLAG";
  protected final String TEXT_118 = "_DEFAULT";
  protected final String TEXT_119 = NL + "\t}" + NL + "\t" + NL + "\t";
  protected final String TEXT_120 = NL + "\t/**" + NL + "\t * *********************************" + NL + "\t * VirSat Specific Code Generation" + NL + "\t * *********************************" + NL + "\t * This method handles unresolved references into otehr resources." + NL + "\t * When ever a proxy object in the DVLM model is resolved" + NL + "\t * This method will check the object and place an error to the resource" + NL + "\t * if it cannot be resolved " + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Override" + NL + "\tpublic ";
  protected final String TEXT_121 = " eResolveProxy(";
  protected final String TEXT_122 = " proxy) {" + NL + "\t\tEObject resolvedProxy = super.eResolveProxy(proxy);" + NL + "\t\treturn ";
  protected final String TEXT_123 = ".checkProxyObject(resolvedProxy, proxy, this.eResource());" + NL + "\t}" + NL + "\t";
  protected final String TEXT_124 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_125 = NL + "\t@Override";
  protected final String TEXT_126 = " eStaticClass()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_127 = ";" + NL + "\t}" + NL;
  protected final String TEXT_128 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected static final int ESTATIC_FEATURE_COUNT = ";
  protected final String TEXT_129 = NL + "\tprotected int eStaticFeatureCount()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_130 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * This is specialized for the more specific element type known in this context." + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_131 = NL + "\tpublic ";
  protected final String TEXT_132 = "()" + NL + "\t{";
  protected final String TEXT_133 = " list = (";
  protected final String TEXT_134 = "();" + NL + "\t\tif (list.isEmpty()) return ";
  protected final String TEXT_135 = "_EEMPTY_ARRAY;";
  protected final String TEXT_136 = NL + "\t\tif (";
  protected final String TEXT_137 = " == null || ";
  protected final String TEXT_138 = ".isEmpty()) return ";
  protected final String TEXT_139 = "_EEMPTY_ARRAY;" + NL + "\t\t";
  protected final String TEXT_140 = NL + "\t\tlist.shrink();" + NL + "\t\treturn (";
  protected final String TEXT_141 = "[])list.data();" + NL + "\t}" + NL;
  protected final String TEXT_142 = "_";
  protected final String TEXT_143 = " = (";
  protected final String TEXT_144 = ")eVirtualGet(";
  protected final String TEXT_145 = ");";
  protected final String TEXT_146 = " == null)" + NL + "\t\t{";
  protected final String TEXT_147 = NL + "\t\t\teVirtualSet(";
  protected final String TEXT_148 = ", ";
  protected final String TEXT_149 = " = new ";
  protected final String TEXT_150 = NL + "\t\t\t";
  protected final String TEXT_151 = NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_152 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * This is specialized for the more specific type known in this context." + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_153 = " basicSet";
  protected final String TEXT_154 = " new";
  protected final String TEXT_155 = " msgs)" + NL + "\t{" + NL + "\t\treturn super.basicSet";
  protected final String TEXT_156 = "(new";
  protected final String TEXT_157 = ", msgs);" + NL + "\t}" + NL;
  protected final String TEXT_158 = NL + "\tpublic void set";
  protected final String TEXT_159 = ")" + NL + "\t{" + NL + "\t\tsuper.set";
  protected final String TEXT_160 = ");" + NL + "\t}" + NL;
  protected final String TEXT_161 = NL + "\t";
  protected final String TEXT_162 = "();" + NL;
  protected final String TEXT_163 = " get";
  protected final String TEXT_164 = "(int index);" + NL;
  protected final String TEXT_165 = "(int index)" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_166 = "().get(index);" + NL + "\t}" + NL;
  protected final String TEXT_167 = NL + "\tint get";
  protected final String TEXT_168 = "Length();" + NL;
  protected final String TEXT_169 = NL + "\tpublic int get";
  protected final String TEXT_170 = "Length()" + NL + "\t{";
  protected final String TEXT_171 = NL + "\t\treturn ";
  protected final String TEXT_172 = "().size();";
  protected final String TEXT_173 = " == null ? 0 : ";
  protected final String TEXT_174 = ".size();";
  protected final String TEXT_175 = NL + "\t}" + NL;
  protected final String TEXT_176 = NL + "\tvoid set";
  protected final String TEXT_177 = "[] new";
  protected final String TEXT_178 = ");" + NL;
  protected final String TEXT_179 = ")" + NL + "\t{" + NL + "\t\t((";
  protected final String TEXT_180 = "()).setData(new";
  protected final String TEXT_181 = ".length, new";
  protected final String TEXT_182 = "(int index, ";
  protected final String TEXT_183 = " element);" + NL;
  protected final String TEXT_184 = " element)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_185 = "().set(index, element);" + NL + "\t}" + NL;
  protected final String TEXT_186 = NL + "\t/**" + NL + "\t * Returns the value of the '<em><b>";
  protected final String TEXT_187 = "</b></em>' ";
  protected final String TEXT_188 = ".";
  protected final String TEXT_189 = NL + "\t * The key is of type ";
  protected final String TEXT_190 = "list of {@link ";
  protected final String TEXT_191 = "}";
  protected final String TEXT_192 = "{@link ";
  protected final String TEXT_193 = "," + NL + "\t * and the value is of type ";
  protected final String TEXT_194 = ",";
  protected final String TEXT_195 = NL + "\t * The list contents are of type {@link ";
  protected final String TEXT_196 = NL + "\t * The default value is <code>";
  protected final String TEXT_197 = "</code>.";
  protected final String TEXT_198 = NL + "\t * The literals are from the enumeration {@link ";
  protected final String TEXT_199 = "}.";
  protected final String TEXT_200 = NL + "\t * It is bidirectional and its opposite is '{@link ";
  protected final String TEXT_201 = "</em>}'.";
  protected final String TEXT_202 = NL + "\t * <!-- begin-user-doc -->";
  protected final String TEXT_203 = NL + "\t * <p>" + NL + "\t * If the meaning of the '<em>";
  protected final String TEXT_204 = "</em>' ";
  protected final String TEXT_205 = " isn't clear," + NL + "\t * there really should be more of a description here..." + NL + "\t * </p>";
  protected final String TEXT_206 = NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_207 = NL + "\t * <!-- begin-model-doc -->" + NL + "\t * ";
  protected final String TEXT_208 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_209 = NL + "\t * @return the value of the '<em>";
  protected final String TEXT_210 = NL + "\t * @see ";
  protected final String TEXT_211 = NL + "\t * @see #isSet";
  protected final String TEXT_212 = NL + "\t * @see #unset";
  protected final String TEXT_213 = NL + "\t * @see #set";
  protected final String TEXT_214 = NL + "\t * @model ";
  protected final String TEXT_215 = NL + "\t *        ";
  protected final String TEXT_216 = NL + "\t * @model";
  protected final String TEXT_217 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_218 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return value or object of type '{@code ";
  protected final String TEXT_219 = "}'." + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_220 = ")eDynamicGet(";
  protected final String TEXT_221 = " - ESTATIC_FEATURE_COUNT";
  protected final String TEXT_222 = ", true, ";
  protected final String TEXT_223 = ").";
  protected final String TEXT_224 = ")eGet(";
  protected final String TEXT_225 = ", true)";
  protected final String TEXT_226 = "__ESETTING_DELEGATE.dynamicGet(this, null, 0, true, false)";
  protected final String TEXT_227 = NL + "       \t\t";
  protected final String TEXT_228 = ";" + NL + "       \t ";
  protected final String TEXT_229 = ";" + NL + "\t\t ";
  protected final String TEXT_230 = NL + "\t\tif (eContainerFeatureID() != ";
  protected final String TEXT_231 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_232 = "eContainer";
  protected final String TEXT_233 = "eInternalContainer";
  protected final String TEXT_234 = "();";
  protected final String TEXT_235 = " != null && ";
  protected final String TEXT_236 = ".eIsProxy())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_237 = " old";
  protected final String TEXT_238 = ";" + NL + "\t\t\t";
  protected final String TEXT_239 = "eResolveProxy(old";
  protected final String TEXT_240 = ");" + NL + "\t\t\tif (";
  protected final String TEXT_241 = " != old";
  protected final String TEXT_242 = ")" + NL + "\t\t\t{";
  protected final String TEXT_243 = NL + "\t\t\t\t";
  protected final String TEXT_244 = " msgs = old";
  protected final String TEXT_245 = ".eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_246 = ", null, null);";
  protected final String TEXT_247 = " msgs =  old";
  protected final String TEXT_248 = ".eInverseRemove(this, ";
  protected final String TEXT_249 = ".class, null);";
  protected final String TEXT_250 = NL + "\t\t\t\tif (new";
  protected final String TEXT_251 = ".eInternalContainer() == null)" + NL + "\t\t\t\t{";
  protected final String TEXT_252 = NL + "\t\t\t\t\tmsgs = new";
  protected final String TEXT_253 = ".eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_254 = ", null, msgs);";
  protected final String TEXT_255 = NL + "\t\t\t\t\tmsgs =  new";
  protected final String TEXT_256 = ".eInverseAdd(this, ";
  protected final String TEXT_257 = ".class, msgs);";
  protected final String TEXT_258 = NL + "\t\t\t\t}" + NL + "\t\t\t\tif (msgs != null) msgs.dispatch();";
  protected final String TEXT_259 = NL + "\t\t\t\teVirtualSet(";
  protected final String TEXT_260 = NL + "\t\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\t\teNotify(new ";
  protected final String TEXT_261 = "(this, ";
  protected final String TEXT_262 = ".RESOLVE, ";
  protected final String TEXT_263 = ", old";
  protected final String TEXT_264 = "));";
  protected final String TEXT_265 = NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_266 = NL + "\t\treturn (";
  protected final String TEXT_267 = " & ";
  protected final String TEXT_268 = "_EFLAG) != 0;";
  protected final String TEXT_269 = "_EFLAG_VALUES[(";
  protected final String TEXT_270 = "_EFLAG) >>> ";
  protected final String TEXT_271 = "_EFLAG_OFFSET];";
  protected final String TEXT_272 = " = basicGet";
  protected final String TEXT_273 = "();" + NL + "\t\treturn ";
  protected final String TEXT_274 = ".eIsProxy() ? ";
  protected final String TEXT_275 = "eResolveProxy((";
  protected final String TEXT_276 = ") : ";
  protected final String TEXT_277 = NL + "\t\treturn new ";
  protected final String TEXT_278 = "((";
  protected final String TEXT_279 = ".Internal)((";
  protected final String TEXT_280 = ".Internal.Wrapper)get";
  protected final String TEXT_281 = "()).featureMap().";
  protected final String TEXT_282 = "list(";
  protected final String TEXT_283 = ")get";
  protected final String TEXT_284 = "().";
  protected final String TEXT_285 = NL + "\t\treturn ((";
  protected final String TEXT_286 = "()).featureMap().list(";
  protected final String TEXT_287 = NL + "\t\treturn get";
  protected final String TEXT_288 = "().list(";
  protected final String TEXT_289 = "()).featureMap().get(";
  protected final String TEXT_290 = "get";
  protected final String TEXT_291 = "().get(";
  protected final String TEXT_292 = NL + "\t\t// *********************************" + NL + "\t\t//  VirSat Specific Code Generation" + NL + "\t\t// *********************************" + NL + "     \treturn ";
  protected final String TEXT_293 = ".getFullQualifiedId(this);";
  protected final String TEXT_294 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_295 = "' ";
  protected final String TEXT_296 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT";
  protected final String TEXT_297 = NL + "\t\t// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting" + NL + "\t\t// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.";
  protected final String TEXT_298 = "EcoreEMap";
  protected final String TEXT_299 = "BasicFeatureMap";
  protected final String TEXT_300 = "EcoreEList";
  protected final String TEXT_301 = " should be used.";
  protected final String TEXT_302 = NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_303 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return value of type '{@code ";
  protected final String TEXT_304 = " basicGet";
  protected final String TEXT_305 = ", false, ";
  protected final String TEXT_306 = "__ESETTING_DELEGATE.dynamicGet(this, null, 0, false, false)";
  protected final String TEXT_307 = ")eInternalContainer();";
  protected final String TEXT_308 = ")((";
  protected final String TEXT_309 = ", false);";
  protected final String TEXT_310 = NL + "\t\t// -> do not perform proxy resolution" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_311 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param new";
  protected final String TEXT_312 = " new value to be of type '{@code ";
  protected final String TEXT_313 = "}' to be set. " + NL + "\t * @param msgs notifications of type '{@code ";
  protected final String TEXT_314 = "}'." + NL + "\t * @return notification of type '{@code ";
  protected final String TEXT_315 = " msgs)" + NL + "\t{";
  protected final String TEXT_316 = NL + "\t\tmsgs = eBasicSetContainer((";
  protected final String TEXT_317 = ")new";
  protected final String TEXT_318 = ", msgs);";
  protected final String TEXT_319 = NL + "\t\treturn msgs;";
  protected final String TEXT_320 = NL + "\t\tmsgs = eDynamicInverseAdd((";
  protected final String TEXT_321 = NL + "\t\tObject old";
  protected final String TEXT_322 = " = eVirtualSet(";
  protected final String TEXT_323 = ", new";
  protected final String TEXT_324 = ";" + NL + "\t\t";
  protected final String TEXT_325 = " = new";
  protected final String TEXT_326 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_327 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_328 = NL + "\t\tboolean old";
  protected final String TEXT_329 = "ESet = (";
  protected final String TEXT_330 = "_ESETFLAG) != 0;";
  protected final String TEXT_331 = "_ESETFLAG;";
  protected final String TEXT_332 = "ESet = ";
  protected final String TEXT_333 = "ESet;";
  protected final String TEXT_334 = "ESet = true;";
  protected final String TEXT_335 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{";
  protected final String TEXT_336 = " notification = new ";
  protected final String TEXT_337 = ".SET, ";
  protected final String TEXT_338 = "isSetChange ? null : old";
  protected final String TEXT_339 = "old";
  protected final String TEXT_340 = "isSetChange";
  protected final String TEXT_341 = "!old";
  protected final String TEXT_342 = "ESet";
  protected final String TEXT_343 = " == EVIRTUAL_NO_VALUE ? null : old";
  protected final String TEXT_344 = NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}";
  protected final String TEXT_345 = "()).featureMap()).basicAdd(";
  protected final String TEXT_346 = ".Internal)get";
  protected final String TEXT_347 = "()).basicAdd(";
  protected final String TEXT_348 = NL + "\t\t// TODO: implement this method to set the contained '";
  protected final String TEXT_349 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_350 = NL + "\t/**" + NL + "\t * Sets the value of the '{@link ";
  protected final String TEXT_351 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param value the new value of the '<em>";
  protected final String TEXT_352 = NL + "\t * @see #";
  protected final String TEXT_353 = "()" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_354 = " value);" + NL;
  protected final String TEXT_355 = ")" + NL + "\t{";
  protected final String TEXT_356 = NL + "\t\tif (!";
  protected final String TEXT_357 = ".isValid(this, ";
  protected final String TEXT_358 = ")) {" + NL + "\t\t\treturn;" + NL + "\t\t}";
  protected final String TEXT_359 = NL + "\t\teDynamicSet(";
  protected final String TEXT_360 = "new ";
  protected final String TEXT_361 = "new";
  protected final String TEXT_362 = NL + "\t\teSet(";
  protected final String TEXT_363 = "__ESETTING_DELEGATE.dynamicSet(this, null, 0, ";
  protected final String TEXT_364 = NL + "\t\tif (new";
  protected final String TEXT_365 = " != eInternalContainer() || (eContainerFeatureID() != ";
  protected final String TEXT_366 = " && new";
  protected final String TEXT_367 = " != null))" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_368 = ".isAncestor(this, ";
  protected final String TEXT_369 = "))" + NL + "\t\t\t\tthrow new ";
  protected final String TEXT_370 = "(\"Recursive containment not allowed for \" + toString());";
  protected final String TEXT_371 = " msgs = null;" + NL + "\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_372 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_373 = ").eInverseAdd(this, ";
  protected final String TEXT_374 = ".class, msgs);" + NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_375 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_376 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_377 = " != ";
  protected final String TEXT_378 = ")" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_379 = " msgs = null;" + NL + "\t\t\tif (";
  protected final String TEXT_380 = " != null)";
  protected final String TEXT_381 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_382 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_383 = ", null, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_384 = ").eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_385 = ").eInverseRemove(this, ";
  protected final String TEXT_386 = ".class, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_387 = NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_388 = NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_389 = NL + "\t\t\tboolean old";
  protected final String TEXT_390 = "ESet = eVirtualIsSet(";
  protected final String TEXT_391 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_392 = ", !old";
  protected final String TEXT_393 = "ESet));";
  protected final String TEXT_394 = NL + "\t\t}";
  protected final String TEXT_395 = ") ";
  protected final String TEXT_396 = "_EFLAG; else ";
  protected final String TEXT_397 = " &= ~";
  protected final String TEXT_398 = "_EFLAG;";
  protected final String TEXT_399 = " == null) new";
  protected final String TEXT_400 = "_EDEFAULT;" + NL + "\t\t";
  protected final String TEXT_401 = " & ~";
  protected final String TEXT_402 = "_EFLAG | ";
  protected final String TEXT_403 = ".VALUES.indexOf(new";
  protected final String TEXT_404 = "_EFLAG_OFFSET;";
  protected final String TEXT_405 = " == null ? ";
  protected final String TEXT_406 = " : new";
  protected final String TEXT_407 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_408 = "isSetChange ? ";
  protected final String TEXT_409 = " : old";
  protected final String TEXT_410 = " == EVIRTUAL_NO_VALUE ? ";
  protected final String TEXT_411 = NL + "\t\t((";
  protected final String TEXT_412 = "()).featureMap()).set(";
  protected final String TEXT_413 = "()).set(";
  protected final String TEXT_414 = NL + "\t\t// TODO: implement this method to set the '";
  protected final String TEXT_415 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_416 = " basicUnset";
  protected final String TEXT_417 = NL + "\t\treturn eDynamicInverseRemove((";
  protected final String TEXT_418 = "basicGet";
  protected final String TEXT_419 = "(), ";
  protected final String TEXT_420 = "Object old";
  protected final String TEXT_421 = "eVirtualUnset(";
  protected final String TEXT_422 = " = null;";
  protected final String TEXT_423 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_424 = "ESet = false;";
  protected final String TEXT_425 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_426 = ".UNSET, ";
  protected final String TEXT_427 = "isSetChange ? old";
  protected final String TEXT_428 = " : null";
  protected final String TEXT_429 = ", null, ";
  protected final String TEXT_430 = ");" + NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_431 = NL + "\t\t// TODO: implement this method to unset the contained '";
  protected final String TEXT_432 = NL + "\t/**" + NL + "\t * Unsets the value of the '{@link ";
  protected final String TEXT_433 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_434 = NL + "\tvoid unset";
  protected final String TEXT_435 = NL + "\tpublic void unset";
  protected final String TEXT_436 = NL + "\t\teDynamicUnset(";
  protected final String TEXT_437 = NL + "\t\teUnset(";
  protected final String TEXT_438 = "__ESETTING_DELEGATE.dynamicUnset(this, null, 0);";
  protected final String TEXT_439 = " != null) ((";
  protected final String TEXT_440 = ".Unsettable";
  protected final String TEXT_441 = ").unset();";
  protected final String TEXT_442 = " != null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_443 = " msgs = null;";
  protected final String TEXT_444 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_445 = NL + "\t\t\tmsgs = basicUnset";
  protected final String TEXT_446 = "(msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_447 = ", null, null, old";
  protected final String TEXT_448 = " = eVirtualUnset(";
  protected final String TEXT_449 = "_EFLAG_DEFAULT;";
  protected final String TEXT_450 = " : ";
  protected final String TEXT_451 = "()).featureMap()).clear(";
  protected final String TEXT_452 = "()).clear(";
  protected final String TEXT_453 = NL + "\t\t// TODO: implement this method to unset the '";
  protected final String TEXT_454 = NL + "\t/**" + NL + "\t * Returns whether the value of the '{@link ";
  protected final String TEXT_455 = " is set.";
  protected final String TEXT_456 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return whether the value of the '<em>";
  protected final String TEXT_457 = NL + "\tboolean isSet";
  protected final String TEXT_458 = NL + "\tpublic boolean isSet";
  protected final String TEXT_459 = NL + "\t\treturn eDynamicIsSet(";
  protected final String TEXT_460 = NL + "\t\treturn eIsSet(";
  protected final String TEXT_461 = "__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);";
  protected final String TEXT_462 = " != null && ((";
  protected final String TEXT_463 = ").isSet();";
  protected final String TEXT_464 = NL + "\t\treturn eVirtualIsSet(";
  protected final String TEXT_465 = NL + "\t\treturn !((";
  protected final String TEXT_466 = "()).featureMap()).isEmpty(";
  protected final String TEXT_467 = "()).isEmpty(";
  protected final String TEXT_468 = NL + "\t\t// TODO: implement this method to return whether the '";
  protected final String TEXT_469 = " is set" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_470 = NL + "\t/**" + NL + "\t * The cached validation expression for the '{@link #";
  protected final String TEXT_471 = ") <em>";
  protected final String TEXT_472 = "</em>}' invariant operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_473 = ")" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_474 = "__EEXPRESSION = \"";
  protected final String TEXT_475 = NL + "\t/**" + NL + "\t * The cached invocation delegate for the '{@link #";
  protected final String TEXT_476 = "</em>}' operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_477 = ".Internal.InvocationDelegate ";
  protected final String TEXT_478 = "__EINVOCATION_DELEGATE = ((";
  protected final String TEXT_479 = ").getInvocationDelegate();" + NL;
  protected final String TEXT_480 = NL + "\t/**";
  protected final String TEXT_481 = NL + "\t * <!-- begin-model-doc -->";
  protected final String TEXT_482 = NL + "\t * ";
  protected final String TEXT_483 = NL + "\t * @param ";
  protected final String TEXT_484 = NL + "\t *   ";
  protected final String TEXT_485 = NL + "\t * @return the value of the '<em>{@code ";
  protected final String TEXT_486 = "}</em>' operation as '{@code ";
  protected final String TEXT_487 = "}'.";
  protected final String TEXT_488 = NL + "\t{";
  protected final String TEXT_489 = NL + "\t\treturn" + NL + "\t\t\t";
  protected final String TEXT_490 = ".validate" + NL + "\t\t\t\t(";
  protected final String TEXT_491 = "," + NL + "\t\t\t\t this," + NL + "\t\t\t\t ";
  protected final String TEXT_492 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_493 = "," + NL + "\t\t\t\t \"";
  protected final String TEXT_494 = "\",";
  protected final String TEXT_495 = NL + "\t\t\t\t ";
  protected final String TEXT_496 = "__EEXPRESSION," + NL + "\t\t\t\t ";
  protected final String TEXT_497 = ".ERROR," + NL + "\t\t\t\t ";
  protected final String TEXT_498 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t ";
  protected final String TEXT_499 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// -> specify the condition that violates the invariant" + NL + "\t\t// -> verify the details of the diagnostic, including severity and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_500 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_501 = ".add" + NL + "\t\t\t\t\t(new ";
  protected final String TEXT_502 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_503 = ".ERROR," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_504 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_505 = "," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_506 = ".INSTANCE.getString(\"_UI_GenericInvariant_diagnostic\", new Object[] { \"";
  protected final String TEXT_507 = "\", ";
  protected final String TEXT_508 = ".getObjectLabel(this, ";
  protected final String TEXT_509 = ") }),";
  protected final String TEXT_510 = NL + "\t\t\t\t\t\t new Object [] { this }));" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_511 = NL + "\t\ttry" + NL + "\t\t{";
  protected final String TEXT_512 = "__EINVOCATION_DELEGATE.dynamicInvoke(this, ";
  protected final String TEXT_513 = ".UnmodifiableEList<Object>(";
  protected final String TEXT_514 = "null";
  protected final String TEXT_515 = NL + "\t\t\treturn ";
  protected final String TEXT_516 = NL + "\t\t}" + NL + "\t\tcatch (";
  protected final String TEXT_517 = " ite)" + NL + "\t\t{" + NL + "\t\t\tthrow new ";
  protected final String TEXT_518 = "(ite);" + NL + "\t\t}";
  protected final String TEXT_519 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_520 = " eInverseAdd(";
  protected final String TEXT_521 = " otherEnd, int featureID, ";
  protected final String TEXT_522 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_523 = ")" + NL + "\t\t{";
  protected final String TEXT_524 = NL + "\t\t\tcase ";
  protected final String TEXT_525 = ":";
  protected final String TEXT_526 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_527 = ".InternalMapView";
  protected final String TEXT_528 = "()).eMap()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_529 = NL + "\t\t\t\treturn (";
  protected final String TEXT_530 = "()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_531 = NL + "\t\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);";
  protected final String TEXT_532 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_533 = ")otherEnd, msgs);";
  protected final String TEXT_534 = NL + "\t\t\t\treturn eBasicSetContainer(otherEnd, ";
  protected final String TEXT_535 = NL + "\t\t\t\tif (";
  protected final String TEXT_536 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_537 = NL + "\t\treturn super.eInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_538 = NL + "\t\treturn eDynamicInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_539 = " eInverseRemove(";
  protected final String TEXT_540 = "()).eMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_541 = ".Internal.Wrapper)";
  protected final String TEXT_542 = "()).featureMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_543 = "()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_544 = NL + "\t\t\t\treturn eBasicSetContainer(null, ";
  protected final String TEXT_545 = NL + "\t\t\t\treturn basicUnset";
  protected final String TEXT_546 = "(msgs);";
  protected final String TEXT_547 = "(null, msgs);";
  protected final String TEXT_548 = NL + "\t\treturn super.eInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_549 = NL + "\t\treturn eDynamicInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_550 = " eBasicRemoveFromContainerFeature(";
  protected final String TEXT_551 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (eContainerFeatureID()";
  protected final String TEXT_552 = ":" + NL + "\t\t\t\treturn eInternalContainer().eInverseRemove(this, ";
  protected final String TEXT_553 = NL + "\t\treturn super.eBasicRemoveFromContainerFeature(msgs);";
  protected final String TEXT_554 = NL + "\t\treturn eDynamicBasicRemoveFromContainer(msgs);";
  protected final String TEXT_555 = NL + "\tpublic Object eGet(int featureID, boolean resolve, boolean coreType)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_556 = NL + "\t\t\t\treturn ";
  protected final String TEXT_557 = "() ? Boolean.TRUE : Boolean.FALSE;";
  protected final String TEXT_558 = NL + "\t\t\t\treturn new ";
  protected final String TEXT_559 = "());";
  protected final String TEXT_560 = NL + "\t\t\t\tif (resolve) return ";
  protected final String TEXT_561 = "();" + NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_562 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_563 = "()).eMap();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_564 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_565 = "();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_566 = "().map();";
  protected final String TEXT_567 = "()).featureMap();" + NL + "\t\t\t\treturn ";
  protected final String TEXT_568 = "();" + NL + "\t\t\t\treturn ((";
  protected final String TEXT_569 = "()).getWrapper();";
  protected final String TEXT_570 = NL + "\t\treturn super.eGet(featureID, resolve, coreType);";
  protected final String TEXT_571 = NL + "\t\treturn eDynamicGet(featureID, resolve, coreType);";
  protected final String TEXT_572 = NL + "\tpublic void eSet(int featureID, Object newValue)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_573 = NL + "\t\t\t\t((";
  protected final String TEXT_574 = "()).featureMap()).set(newValue);";
  protected final String TEXT_575 = "()).set(newValue);";
  protected final String TEXT_576 = ".Setting)((";
  protected final String TEXT_577 = "()).eMap()).set(newValue);";
  protected final String TEXT_578 = ".Setting)";
  protected final String TEXT_579 = "().clear();" + NL + "\t\t\t\t";
  protected final String TEXT_580 = "().addAll((";
  protected final String TEXT_581 = "<? extends ";
  protected final String TEXT_582 = ">";
  protected final String TEXT_583 = ")newValue);";
  protected final String TEXT_584 = NL + "\t\t\t\tset";
  protected final String TEXT_585 = "(((";
  protected final String TEXT_586 = ")newValue).";
  protected final String TEXT_587 = "newValue);";
  protected final String TEXT_588 = NL + "\t\t\t\treturn;";
  protected final String TEXT_589 = NL + "\t\tsuper.eSet(featureID, newValue);";
  protected final String TEXT_590 = NL + "\t\teDynamicSet(featureID, newValue);";
  protected final String TEXT_591 = NL + "\tpublic void eUnset(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_592 = "()).featureMap().clear();";
  protected final String TEXT_593 = "().clear();";
  protected final String TEXT_594 = NL + "\t\t\t\tunset";
  protected final String TEXT_595 = ")null);";
  protected final String TEXT_596 = NL + "\t\tsuper.eUnset(featureID);";
  protected final String TEXT_597 = NL + "\t\teDynamicUnset(featureID);";
  protected final String TEXT_598 = NL + "\tpublic boolean eIsSet(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_599 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_600 = NL + "\t\t\t\treturn !((";
  protected final String TEXT_601 = "()).featureMap().isEmpty();";
  protected final String TEXT_602 = " != null && !";
  protected final String TEXT_603 = ".featureMap().isEmpty();";
  protected final String TEXT_604 = ".isEmpty();";
  protected final String TEXT_605 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_606 = NL + "\t\t\t\treturn !";
  protected final String TEXT_607 = "().isEmpty();";
  protected final String TEXT_608 = " != null;";
  protected final String TEXT_609 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_610 = ") != null;";
  protected final String TEXT_611 = NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_612 = "() != null;";
  protected final String TEXT_613 = "_EFLAG) != 0) != ";
  protected final String TEXT_614 = "_EFLAG) != ";
  protected final String TEXT_615 = ") != ";
  protected final String TEXT_616 = "() != ";
  protected final String TEXT_617 = " != null : !";
  protected final String TEXT_618 = ".equals(";
  protected final String TEXT_619 = "() != null : !";
  protected final String TEXT_620 = NL + "\t\treturn super.eIsSet(featureID);";
  protected final String TEXT_621 = NL + "\t\treturn eDynamicIsSet(featureID);";
  protected final String TEXT_622 = NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class";
  protected final String TEXT_623 = " baseClass)" + NL + "\t{";
  protected final String TEXT_624 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_625 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (derivedFeatureID";
  protected final String TEXT_626 = NL + "\t\t\t\tcase ";
  protected final String TEXT_627 = ": return ";
  protected final String TEXT_628 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_629 = NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);" + NL + "\t}";
  protected final String TEXT_630 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_631 = NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class";
  protected final String TEXT_632 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_633 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID";
  protected final String TEXT_634 = NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_635 = NL + "\tpublic int eDerivedOperationID(int baseOperationID, Class";
  protected final String TEXT_636 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID)" + NL + "\t\t\t{";
  protected final String TEXT_637 = NL + "\t\t\t\tdefault: return super.eDerivedOperationID(baseOperationID, baseClass);" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_638 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID";
  protected final String TEXT_639 = NL + "\t\treturn super.eDerivedOperationID(baseOperationID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_640 = NL + "\tprotected Object[] eVirtualValues()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_641 = ";" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_642 = NL + "\tprotected void eSetVirtualValues(Object[] newValues)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_643 = " = newValues;" + NL + "\t}" + NL;
  protected final String TEXT_644 = NL + "\tprotected int eVirtualIndexBits(int offset)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_645 = " :" + NL + "\t\t\t\treturn ";
  protected final String TEXT_646 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_647 = NL + "\tprotected void eSetVirtualIndexBits(int offset, int newIndexBits)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_648 = " :" + NL + "\t\t\t\t";
  protected final String TEXT_649 = " = newIndexBits;" + NL + "\t\t\t\tbreak;";
  protected final String TEXT_650 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_651 = NL + "\t@SuppressWarnings(";
  protected final String TEXT_652 = "\"unchecked\"";
  protected final String TEXT_653 = "{\"rawtypes\", \"unchecked\" }";
  protected final String TEXT_654 = NL + "\tpublic Object eInvoke(int operationID, ";
  protected final String TEXT_655 = " arguments) throws ";
  protected final String TEXT_656 = NL + "\t{" + NL + "\t\tswitch (operationID";
  protected final String TEXT_657 = NL + "\t\t\t\ttry" + NL + "\t\t\t\t{";
  protected final String TEXT_658 = "arguments.get(";
  protected final String TEXT_659 = ");" + NL + "\t\t\t\t";
  protected final String TEXT_660 = "return null;";
  protected final String TEXT_661 = "return ";
  protected final String TEXT_662 = NL + "\t\t\t\t}" + NL + "\t\t\t\tcatch (";
  protected final String TEXT_663 = " throwable)" + NL + "\t\t\t\t{" + NL + "\t\t\t\t\tthrow new ";
  protected final String TEXT_664 = "(throwable);" + NL + "\t\t\t\t}";
  protected final String TEXT_665 = NL + "\t\treturn super.eInvoke(operationID, arguments);";
  protected final String TEXT_666 = NL + "\t\treturn eDynamicInvoke(operationID, arguments);";
  protected final String TEXT_667 = NL + "\tpublic String toString()" + NL + "\t{" + NL + "\t\tif (eIsProxy()) return super.toString();" + NL + "" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());";
  protected final String TEXT_668 = NL + "\t\tresult.append(\" (";
  protected final String TEXT_669 = ": \");";
  protected final String TEXT_670 = NL + "\t\tresult.append(\", ";
  protected final String TEXT_671 = NL + "\t\tif (eVirtualIsSet(";
  protected final String TEXT_672 = ")) result.append(eVirtualGet(";
  protected final String TEXT_673 = ")); else result.append(\"<unset>\");";
  protected final String TEXT_674 = "_ESETFLAG) != 0";
  protected final String TEXT_675 = ") result.append((";
  protected final String TEXT_676 = "_EFLAG) != 0); else result.append(\"<unset>\");";
  protected final String TEXT_677 = ") result.append(";
  protected final String TEXT_678 = "_EFLAG_OFFSET]); else result.append(\"<unset>\");";
  protected final String TEXT_679 = "); else result.append(\"<unset>\");";
  protected final String TEXT_680 = NL + "\t\tresult.append(eVirtualGet(";
  protected final String TEXT_681 = NL + "\t\tresult.append((";
  protected final String TEXT_682 = "_EFLAG) != 0);";
  protected final String TEXT_683 = NL + "\t\tresult.append(";
  protected final String TEXT_684 = "_EFLAG_OFFSET]);";
  protected final String TEXT_685 = NL + "\t\tresult.append(')');" + NL + "\t\treturn result.toString();" + NL + "\t}" + NL;
  protected final String TEXT_686 = NL + "\tprotected int hash = -1;" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int getHash()" + NL + "\t{" + NL + "\t\tif (hash == -1)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_687 = " theKey = getKey();" + NL + "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + NL + "\t\t}" + NL + "\t\treturn hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setHash(int hash)" + NL + "\t{" + NL + "\t\tthis.hash = hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_688 = " getKey()" + NL + "\t{";
  protected final String TEXT_689 = "(getTypedKey());";
  protected final String TEXT_690 = NL + "\t\treturn getTypedKey();";
  protected final String TEXT_691 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setKey(";
  protected final String TEXT_692 = " key)" + NL + "\t{";
  protected final String TEXT_693 = NL + "\t\tgetTypedKey().addAll(";
  protected final String TEXT_694 = "key);";
  protected final String TEXT_695 = NL + "\t\tsetTypedKey(key);";
  protected final String TEXT_696 = NL + "\t\tsetTypedKey(((";
  protected final String TEXT_697 = ")key).";
  protected final String TEXT_698 = NL + "\t\tsetTypedKey((";
  protected final String TEXT_699 = ")key);";
  protected final String TEXT_700 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_701 = " getValue()" + NL + "\t{";
  protected final String TEXT_702 = "(getTypedValue());";
  protected final String TEXT_703 = NL + "\t\treturn getTypedValue();";
  protected final String TEXT_704 = " setValue(";
  protected final String TEXT_705 = " value)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_706 = " oldValue = getValue();";
  protected final String TEXT_707 = NL + "\t\tgetTypedValue().clear();" + NL + "\t\tgetTypedValue().addAll(";
  protected final String TEXT_708 = "value);";
  protected final String TEXT_709 = NL + "\t\tsetTypedValue(value);";
  protected final String TEXT_710 = NL + "\t\tsetTypedValue(((";
  protected final String TEXT_711 = ")value).";
  protected final String TEXT_712 = NL + "\t\tsetTypedValue((";
  protected final String TEXT_713 = ")value);";
  protected final String TEXT_714 = NL + "\t\treturn oldValue;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_715 = " getEMap()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_716 = " container = eContainer();" + NL + "\t\treturn container == null ? null : (";
  protected final String TEXT_717 = ")container.eGet(eContainmentFeature());" + NL + "\t}" + NL;
  protected final String TEXT_718 = NL + "} //";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * Copyright (c) 2002-2011 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   DLR - customization for Virtual Satellite
 */

    final GenClass genClass = (GenClass)((Object[])argument)[0]; final GenPackage genPackage = genClass.getGenPackage(); final GenModel genModel=genPackage.getGenModel();
    final String INTERFACE_NAME_UUID = "IUuid";
    final String INTERFACE_NAME_QUALIFIEDNAME_GETTER = "getFullQualifiedName";
    final String CLASS_NAME_STRUCTURAL_ELEMENT_INSTANCE = "StructuralElementInstance";
    final String CLASS_NAME_REFERENCE_PROPERTY_INSTANCE = "ReferencePropertyInstance";
    final boolean isJDK50 = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50;
    final boolean isInterface = Boolean.TRUE.equals(((Object[])argument)[1]); final boolean isImplementation = Boolean.TRUE.equals(((Object[])argument)[2]);
    final boolean isGWT = genModel.getRuntimePlatform() == GenRuntimePlatform.GWT;
    final String publicStaticFinalFlag = isImplementation ? "public static final " : "";
    final String singleWildcard = isJDK50 ? "<?>" : "";
    final String negativeOffsetCorrection = genClass.hasOffsetCorrection() ? " - " + genClass.getOffsetCorrectionField(null) : "";
    final String positiveOffsetCorrection = genClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(null) : "";
    final String negativeOperationOffsetCorrection = genClass.hasOffsetCorrection() ? " - EOPERATION_OFFSET_CORRECTION" : "";
    final String positiveOperationOffsetCorrection = genClass.hasOffsetCorrection() ? " + EOPERATION_OFFSET_CORRECTION" : "";
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    {GenBase copyrightHolder = argument instanceof GenBase ? (GenBase)argument : argument instanceof Object[] && ((Object[])argument)[0] instanceof GenBase ? (GenBase)((Object[])argument)[0] : null;
    if (copyrightHolder != null && copyrightHolder.hasCopyright()) {
    stringBuffer.append(TEXT_3);
    stringBuffer.append(copyrightHolder.getCopyright(copyrightHolder.getGenModel().getIndentation(stringBuffer)));
    }}
    stringBuffer.append(TEXT_4);
    if (isInterface) {
    stringBuffer.append(TEXT_5);
    stringBuffer.append(genPackage.getInterfacePackageName());
    stringBuffer.append(TEXT_6);
    } else {
    stringBuffer.append(TEXT_5);
    stringBuffer.append(genPackage.getClassPackageName());
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_7);
    
/**
 * *********************************
 * VirSat Specific Code Generation
 * *********************************
 *
 * Loop over all Interfaces and Extended Classes to fint the one defining UUIDs
 * Remember it in case it exists. Information will be used later in the code to 
 * Always generate a new UUID once an object derived from IUuid is created.
 */
boolean hasUUID = false;
for (GenClass iGenClass : genClass.getAllBaseGenClasses()) {
	if (INTERFACE_NAME_UUID.equals(iGenClass.getInterfaceName())) {
		hasUUID = true;
		break;
	}
}

    stringBuffer.append(TEXT_7);
    genModel.markImportLocation(stringBuffer, genPackage);
    if (isImplementation) { genClass.addClassPsuedoImports(); }
    stringBuffer.append(TEXT_7);
    if (isInterface) {
    stringBuffer.append(TEXT_8);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_9);
    if (genClass.hasDocumentation()) {
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genClass.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_11);
    }
    stringBuffer.append(TEXT_12);
    if (!genClass.getGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_13);
    for (GenFeature genFeature : genClass.getGenFeatures()) {
    if (!genFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_17);
    }
    }
    stringBuffer.append(TEXT_18);
    }
    stringBuffer.append(TEXT_12);
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genClass.getClassifierAccessorName());
    stringBuffer.append(TEXT_21);
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genClass.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_22);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_23);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_24);
    }}
    if (genClass.needsRootExtendsInterfaceExtendsTag()) {
    stringBuffer.append(TEXT_25);
    stringBuffer.append(genModel.getImportedName(genModel.getRootExtendsInterface()));
    }
    stringBuffer.append(TEXT_26);
    //Class/interface.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_27);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_9);
    if (!genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_28);
    for (GenFeature genFeature : genClass.getImplementedGenFeatures()) {
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genClass.getQualifiedClassName());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_17);
    }
    stringBuffer.append(TEXT_18);
    }
    stringBuffer.append(TEXT_29);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_30);
    if (genClass.isAbstract()) {
    stringBuffer.append(TEXT_31);
    }
    stringBuffer.append(TEXT_32);
    stringBuffer.append(genClass.getClassName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(genClass.getClassExtends());
    stringBuffer.append(genClass.getClassImplements());
    } else {
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(genClass.getInterfaceExtends());
    }
    stringBuffer.append(TEXT_34);
    if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_35);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_7);
    }
    if (isImplementation && genModel.getDriverNumber() != null) {
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_38);
    stringBuffer.append(genModel.getDriverNumber());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_7);
    }
    if (isImplementation && genClass.isJavaIOSerializable()) {
    stringBuffer.append(TEXT_40);
    }
    if (isImplementation && genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_41);
    if (isGWT) {
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_43);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_44);
    }
    { List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
    if (!eVirtualIndexBitFields.isEmpty()) {
    for (String eVirtualIndexBitField : eVirtualIndexBitFields) {
    stringBuffer.append(TEXT_45);
    if (isGWT) {
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_46);
    stringBuffer.append(eVirtualIndexBitField);
    stringBuffer.append(TEXT_44);
    }
    }
    }
    }
    if (isImplementation && genClass.isModelRoot() && genModel.isBooleanFlagsEnabled() && genModel.getBooleanFlagsReservedBits() == -1) {
    stringBuffer.append(TEXT_47);
    if (isGWT) {
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_46);
    stringBuffer.append(genModel.getBooleanFlagsField());
    stringBuffer.append(TEXT_48);
    }
    if (isImplementation && !genModel.isReflectiveDelegation()) {
    for (GenFeature genFeature : genClass.getReifiedGenFeatures()) {
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) { String rawListItemType = genFeature.getRawListItemType(genClass); int index = rawListItemType.indexOf('['); String head = rawListItemType; String tail = ""; if (index != -1) { head = rawListItemType.substring(0, index); tail = rawListItemType.substring(index); } 
    stringBuffer.append(TEXT_49);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_51);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_52);
    if (genFeature.getQualifiedListItemType(genClass).contains("<") || genFeature.getArrayItemType(genClass).contains("<")) {
    stringBuffer.append(TEXT_53);
    }
    stringBuffer.append(TEXT_54);
    stringBuffer.append(rawListItemType);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_56);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_44);
    }
    }
    for (GenFeature genFeature : genClass.getDeclaredFieldGenFeatures()) {
    if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_58);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_52);
    if (isGWT) {
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_61);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_62);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_65);
    } else if (genFeature.isListType() || genFeature.isReferenceType()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_52);
    if (isGWT) {
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_61);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_44);
    }
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) { String rawListItemType = genFeature.getRawListItemType(genClass); int index = rawListItemType.indexOf('['); String head = rawListItemType; String tail = ""; if (index != -1) { head = rawListItemType.substring(0, index); tail = rawListItemType.substring(index); } 
    stringBuffer.append(TEXT_49);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_52);
    if (genFeature.getQualifiedListItemType(genClass).contains("<") || genFeature.getArrayItemType(genClass).contains("<")) {
    stringBuffer.append(TEXT_53);
    }
    stringBuffer.append(TEXT_54);
    stringBuffer.append(rawListItemType);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_56);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_44);
    }
    } else {
    if (genFeature.hasEDefault() && (!genFeature.isVolatile() || !genModel.isReflectiveDelegation() && (!genFeature.hasDelegateFeature() || !genFeature.isUnsettable()))) { String staticDefaultValue = genFeature.getStaticDefaultValue();
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_52);
    if (genModel.useGenerics() && genFeature.isListDataType() && genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_70);
    }
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getEDefault());
    if ("".equals(staticDefaultValue)) {
    stringBuffer.append(TEXT_71);
    stringBuffer.append(genFeature.getEcoreFeature().getDefaultValueLiteral());
    stringBuffer.append(TEXT_72);
    } else {
    stringBuffer.append(TEXT_73);
    stringBuffer.append(staticDefaultValue);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genModel.getNonNLS(staticDefaultValue));
    }
    stringBuffer.append(TEXT_7);
    }
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) { int flagIndex = genClass.getFlagIndex(genFeature);
    if (flagIndex > 31 && flagIndex % 32 == 0) {
    stringBuffer.append(TEXT_74);
    if (isGWT) {
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_46);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_48);
    }
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_77);
    stringBuffer.append(flagIndex % 32);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_79);
    if (isJDK50) {
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_80);
    } else {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_81);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_82);
    }
    stringBuffer.append(TEXT_83);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getTypeGenClassifier().getFormattedName());
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_86);
    if (isJDK50) {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_87);
    } else {
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_89);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_92);
    }
    stringBuffer.append(TEXT_44);
    }
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genClass.getFlagSize(genFeature) > 1 ? "s" : "");
    stringBuffer.append(TEXT_94);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_95);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_96);
    stringBuffer.append(genClass.getFlagMask(genFeature));
    stringBuffer.append(TEXT_83);
    if (genFeature.isEnumType()) {
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_97);
    } else {
    stringBuffer.append(flagIndex % 32);
    }
    stringBuffer.append(TEXT_44);
    } else {
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_52);
    if (isGWT) {
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_61);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getSafeName());
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_44);
    }
    }
    }
    if (genClass.isESetField(genFeature)) {
    if (genClass.isESetFlag(genFeature)) { int flagIndex = genClass.getESetFlagIndex(genFeature);
    if (flagIndex > 31 && flagIndex % 32 == 0) {
    stringBuffer.append(TEXT_74);
    if (isGWT) {
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_46);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_48);
    }
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_100);
    stringBuffer.append(flagIndex % 32 );
    stringBuffer.append(TEXT_44);
    } else {
    stringBuffer.append(TEXT_101);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_102);
    if (isGWT) {
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_103);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_104);
    }
    }
    //Class/declaredFieldGenFeature.override.javajetinc
    }
    }
    if (isImplementation && genClass.hasOffsetCorrection() && !genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_105);
    stringBuffer.append(genClass.getOffsetCorrectionField(null));
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_106);
    stringBuffer.append(genClass.getImplementedGenFeatures().get(0).getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genClass.getQualifiedFeatureID(genClass.getImplementedGenFeatures().get(0)));
    stringBuffer.append(TEXT_44);
    }
    if (isImplementation && !genModel.isReflectiveDelegation()) {
    for (GenFeature genFeature : genClass.getImplementedGenFeatures()) { GenFeature reverseFeature = genFeature.getReverse();
    if (reverseFeature != null && reverseFeature.getGenClass().hasOffsetCorrection()) {
    stringBuffer.append(TEXT_105);
    stringBuffer.append(genClass.getOffsetCorrectionField(genFeature));
    stringBuffer.append(TEXT_73);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_106);
    stringBuffer.append(reverseFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_107);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_44);
    }
    }
    }
    if (genModel.isOperationReflection() && isImplementation && genClass.hasOffsetCorrection() && !genClass.getImplementedGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_108);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_109);
    stringBuffer.append(genClass.getImplementedGenOperations().get(0).getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genClass.getQualifiedOperationID(genClass.getImplementedGenOperations().get(0)));
    stringBuffer.append(TEXT_44);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_110);
    if (genModel.isPublicConstructors()) {
    stringBuffer.append(TEXT_111);
    } else {
    stringBuffer.append(TEXT_112);
    }
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genClass.getClassName());
    stringBuffer.append(TEXT_113);
    
		// *********************************
		//  VirSat Specific Code Generation
		// *********************************
		if (hasUUID) {
    stringBuffer.append(TEXT_114);
    }
    for (GenFeature genFeature : genClass.getFlagGenFeaturesWithDefault()) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_117);
    if (!genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_118);
    }
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_119);
    if (!genClass.isAbstract()) {
    stringBuffer.append(TEXT_120);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_121);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_122);
    stringBuffer.append(genModel.getImportedName("de.dlr.sc.virsat.model.dvlm.util.DVLMUnresolvedReferenceException"));
    stringBuffer.append(TEXT_123);
    }
    stringBuffer.append(TEXT_124);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_125);
    }
    stringBuffer.append(TEXT_61);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClass"));
    stringBuffer.append(TEXT_126);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_127);
    }
    if (isImplementation && (genModel.getFeatureDelegation() == GenDelegationKind.REFLECTIVE_LITERAL || genModel.isDynamicDelegation()) && (genClass.getClassExtendsGenClass() == null || (genClass.getClassExtendsGenClass().getGenModel().getFeatureDelegation() != GenDelegationKind.REFLECTIVE_LITERAL && !genClass.getClassExtendsGenClass().getGenModel().isDynamicDelegation()))) {
    if (genClass.hasStaticFeatures()) {
    stringBuffer.append(TEXT_128);
    stringBuffer.append(genClass.getClassExtendsGenClass() == null ? 0 : genClass.getClassExtendsGenClass().getAllGenFeatures().size());
    stringBuffer.append(TEXT_44);
    }
    stringBuffer.append(TEXT_124);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_125);
    }
    stringBuffer.append(TEXT_129);
    stringBuffer.append(genClass.getClassExtendsGenClass() == null ? "0" : genClass.hasStaticFeatures() ? "ESTATIC_FEATURE_COUNT" : Integer.toString(genClass.getClassExtendsGenClass().getAllGenFeatures().size()));
    stringBuffer.append(TEXT_127);
    }
    //Class/reflectiveDelegation.override.javajetinc
    if (isImplementation) {
    new Runnable() { public void run() { GenClass classExtendsGenClass = genClass.getClassExtendsGenClass(); List<GenFeature> classExtendsAllGenFeatures = classExtendsGenClass == null? Collections.<GenFeature>emptyList() : classExtendsGenClass.getAllGenFeatures();
    for (GenFeature genFeature : genClass.getReifiedGenFeatures()) {
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) { String arrayElementType = genFeature.getArrayItemType(genClass);
    stringBuffer.append(TEXT_130);
    if (genModel.useGenerics() && CodeGenUtil.isUncheckedCast(arrayElementType)) {
    stringBuffer.append(TEXT_70);
    }
    if (classExtendsAllGenFeatures.contains(genFeature)) {
    stringBuffer.append(TEXT_125);
    }
    stringBuffer.append(TEXT_131);
    stringBuffer.append(arrayElementType);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_132);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_133);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_134);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_135);
    } else {
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_137);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_138);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_139);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_133);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_140);
    stringBuffer.append(arrayElementType);
    stringBuffer.append(TEXT_141);
    }
    if (genFeature.isGet() && genFeature.isListType()) {
    stringBuffer.append(TEXT_130);
    if (isJDK50) { //Class/getGenFeature.annotations.insert.javajetinc
    }
    if (genFeature.isListType() && genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_70);
    }
    if (classExtendsAllGenFeatures.contains(genFeature)) {
    stringBuffer.append(TEXT_125);
    }
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getGetAccessor());
    if (genClass.hasCollidingGetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_142);
    }
    stringBuffer.append(TEXT_132);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_145);
    }
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_146);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_147);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_145);
    } else {
    stringBuffer.append(TEXT_150);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_151);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_127);
    }
    if (!genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_152);
    if (isJDK50) { //Class/basicSetGenFeature.annotations.insert.javajetinc
    }
    if (classExtendsAllGenFeatures.contains(genFeature)) {
    stringBuffer.append(TEXT_125);
    }
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_153);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_155);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_156);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_157);
    }
    if (genFeature.isSet() && !(!genModel.isReflectiveDelegation() && genFeature.isBasicSet())) {
    stringBuffer.append(TEXT_152);
    if (isJDK50) { //Class/setGenFeature.annotations.insert.javajetinc
    }
    { GenOperation setAccessorOperation = genClass.getSetAccessorOperation(genFeature);
    if (classExtendsAllGenFeatures.contains(genFeature)) {
    stringBuffer.append(TEXT_125);
    }
    stringBuffer.append(TEXT_158);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_142);
    }
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(setAccessorOperation == null ? "new" + genFeature.getCapName() : setAccessorOperation.getGenParameters().get(0).getName());
    stringBuffer.append(TEXT_159);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_142);
    }
    stringBuffer.append(TEXT_88);
    stringBuffer.append(setAccessorOperation == null ? "new" + genFeature.getCapName() : setAccessorOperation.getGenParameters().get(0).getName());
    stringBuffer.append(TEXT_160);
    }
    }
    //Class/genFeatureReified.override.javajetinc
    }}}.run();}
    new Runnable() { public void run() {
    for (GenFeature genFeature : (isImplementation ? genClass.getImplementedGenFeatures() : genClass.getDeclaredGenFeatures())) {
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) { String arrayElementType = genFeature.getArrayItemType(genClass);
    stringBuffer.append(TEXT_124);
    if (!isImplementation) {
    stringBuffer.append(TEXT_161);
    stringBuffer.append(arrayElementType);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_162);
    } else {
    if (genModel.useGenerics() && CodeGenUtil.isUncheckedCast(arrayElementType)) {
    stringBuffer.append(TEXT_70);
    }
    stringBuffer.append(TEXT_131);
    stringBuffer.append(arrayElementType);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_132);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_133);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_134);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_135);
    } else {
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_137);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_138);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_139);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_133);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_140);
    stringBuffer.append(arrayElementType);
    stringBuffer.append(TEXT_141);
    }
    stringBuffer.append(TEXT_124);
    if (!isImplementation) {
    stringBuffer.append(TEXT_161);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_163);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_164);
    } else {
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_163);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_165);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_82);
    }
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_166);
    }
    stringBuffer.append(TEXT_124);
    if (!isImplementation) {
    stringBuffer.append(TEXT_167);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_168);
    } else {
    stringBuffer.append(TEXT_169);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_170);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_172);
    } else {
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_173);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_174);
    }
    stringBuffer.append(TEXT_175);
    }
    stringBuffer.append(TEXT_124);
    if (!isImplementation) {
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_177);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_178);
    } else {
    stringBuffer.append(TEXT_158);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_177);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_179);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_180);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_181);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_160);
    }
    stringBuffer.append(TEXT_124);
    if (!isImplementation) {
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_182);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_183);
    } else {
    stringBuffer.append(TEXT_158);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_182);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_184);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_185);
    }
    }
    if (genFeature.isGet() && (isImplementation || !genFeature.isSuppressedGetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_186);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_187);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_188);
    if (genFeature.isListType() && genFeature.getEcoreFeature().getEGenericType().getETypeParameter() == null) {
    if (genFeature.isMapType()) { GenFeature keyFeature = genFeature.getMapEntryTypeGenClass().getMapEntryKeyFeature(); GenFeature valueFeature = genFeature.getMapEntryTypeGenClass().getMapEntryValueFeature(); 
    stringBuffer.append(TEXT_189);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_190);
    stringBuffer.append(keyFeature.getQualifiedListItemType(genClass));
    stringBuffer.append(TEXT_191);
    } else {
    stringBuffer.append(TEXT_192);
    stringBuffer.append(keyFeature.getType(genClass));
    stringBuffer.append(TEXT_191);
    }
    stringBuffer.append(TEXT_193);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_190);
    stringBuffer.append(valueFeature.getQualifiedListItemType(genClass));
    stringBuffer.append(TEXT_191);
    } else {
    stringBuffer.append(TEXT_192);
    stringBuffer.append(valueFeature.getType(genClass));
    stringBuffer.append(TEXT_191);
    }
    stringBuffer.append(TEXT_194);
    } else if (!genFeature.isWrappedFeatureMapType() && !(genModel.isSuppressEMFMetaData() && "org.eclipse.emf.ecore.EObject".equals(genFeature.getQualifiedListItemType(genClass)))) {
String typeName = genFeature.getQualifiedListItemType(genClass); String head = typeName; String tail = ""; int index = typeName.indexOf('<'); if (index == -1) { index = typeName.indexOf('['); } 
if (index != -1) { head = typeName.substring(0, index); tail = typeName.substring(index).replaceAll("<", "&lt;"); }

    stringBuffer.append(TEXT_195);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_191);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_188);
    }
    } else if (genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_196);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_197);
    }
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_198);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    stringBuffer.append(TEXT_199);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_200);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(reverseGenFeature.getFormattedName());
    stringBuffer.append(TEXT_201);
    }
    }
    stringBuffer.append(TEXT_202);
    if (!genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_203);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_204);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_205);
    }
    stringBuffer.append(TEXT_206);
    if (genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_207);
    stringBuffer.append(genFeature.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_208);
    }
    stringBuffer.append(TEXT_209);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_204);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_188);
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_210);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_211);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_21);
    }
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_212);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_21);
    }
    }
    if (genFeature.isChangeable() && !genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_213);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_82);
    }
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_210);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_21);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_210);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    }
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genFeature.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_214);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_215);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_216);
    }}
    stringBuffer.append(TEXT_217);
    //Class/getGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_218);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_219);
    if (isJDK50) { //Class/getGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_161);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_162);
    } else {
    if (genModel.useGenerics() && ((genFeature.isContainer() || genFeature.isResolveProxies()) && !genFeature.isListType() && !(genModel.isReflectiveDelegation() && genModel.isDynamicDelegation()) && genFeature.isUncheckedCast(genClass) || genFeature.isListType() && !genFeature.isFeatureMapType() && (genModel.isReflectiveDelegation() || genModel.isVirtualDelegation() || genModel.isDynamicDelegation()) || genFeature.isListDataType() && genFeature.hasDelegateFeature() || genFeature.isListType() && genFeature.hasSettingDelegate())) {
    stringBuffer.append(TEXT_70);
    }
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getGetAccessor());
    if (genClass.hasCollidingGetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_142);
    }
    stringBuffer.append(TEXT_132);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_171);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_88);
    }
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_220);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (genClass.hasStaticFeatures()){
    stringBuffer.append(TEXT_221);
    }
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_222);
    stringBuffer.append(!genFeature.isEffectiveSuppressEMFTypes());
    stringBuffer.append(TEXT_82);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_223);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_6);
    } else if (genModel.isReflectiveDelegation()) {
    if (genFeature.hasGetterBody()) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getGetterBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_171);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_88);
    }
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_224);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_225);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_223);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_6);
    }
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_171);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_88);
    }
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_226);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_223);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_6);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_145);
    }
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_146);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_147);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_145);
    } else {
    if (CLASS_NAME_STRUCTURAL_ELEMENT_INSTANCE.equals(genClass.getName())) {
       	  // This code is used in the generator to replace the standard Resolving List in the StructuralElementInstance
       	  // with the DVLM one that knows how to handle the IApplicableFor Interface and the correpsonding model logic
       	  String ecoreListConstructor = genClass.getListConstructor(genFeature);
       	  // System.out.println(genFeature.getSafeName()); 
       	  if (ecoreListConstructor.contains("EObjectContainmentWithInverseEList.Resolving")) {
       	    // Just simply replace the original EList in case of containments to the DVLM Containment List
       	  	String dvlmListName = genModel.getImportedName("de.dlr.sc.virsat.model.dvlm.list.DVLMFilteredContainingWithInverseResolvingEList");
       	  	ecoreListConstructor = ecoreListConstructor.replace("EObjectContainmentWithInverseEList.Resolving", dvlmListName);
       	  } else if (ecoreListConstructor.contains("EObjectContainmentEList.Resolving")) {
       	    // Just simply replace the original EList in case of containments to the DVLM Containment List
       	  	String dvlmListName = genModel.getImportedName("de.dlr.sc.virsat.model.dvlm.list.DVLMFilteredContainingResolvingEList");
       	  	ecoreListConstructor = ecoreListConstructor.replace("EObjectContainmentEList.Resolving", dvlmListName);
       	  } else if (ecoreListConstructor.contains("EObjectResolvingEList")) {
       	    // Just simply replace the original EList in case of references to the DVLM Resolving List
       	  	String dvlmListName = genModel.getImportedName("de.dlr.sc.virsat.model.dvlm.list.DVLMFilteredResolvingEList");
       	  	ecoreListConstructor = ecoreListConstructor.replace("EObjectResolvingEList", dvlmListName);
       	  }
    stringBuffer.append(TEXT_227);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_149);
    stringBuffer.append(ecoreListConstructor);
    stringBuffer.append(TEXT_228);
     } else { 
    stringBuffer.append(TEXT_150);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_229);
    }
    stringBuffer.append(TEXT_115);
    }
    stringBuffer.append(TEXT_151);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_6);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_230);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_231);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_82);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_232);
    } else {
    stringBuffer.append(TEXT_233);
    }
    stringBuffer.append(TEXT_234);
    } else {
    if (genFeature.isResolveProxies()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_145);
    }
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_235);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_236);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_237);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_238);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
    stringBuffer.append(TEXT_239);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_240);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_241);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_242);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_243);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_6);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_243);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_244);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_245);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_246);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_243);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_247);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_248);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_148);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_249);
    }
    stringBuffer.append(TEXT_250);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_251);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_252);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_253);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_254);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_255);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_256);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_148);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_257);
    }
    stringBuffer.append(TEXT_258);
    } else if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_259);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_145);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_261);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_262);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_263);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_264);
    }
    stringBuffer.append(TEXT_265);
    }
    if (!genFeature.isResolveProxies() && genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_145);
    } else if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_268);
    } else {
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_270);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_271);
    }
    } else {
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_6);
    }
    }
    } else {//volatile
    if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_272);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_273);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_235);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_274);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
    stringBuffer.append(TEXT_275);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_276);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_6);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (genFeature.isFeatureMapType()) {
    String featureMapEntryTemplateArgument = isJDK50 ? "<" + genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap") + ".Entry>" : "";
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_277);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_279);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_280);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_281);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_282);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_264);
    } else {
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_283);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_284);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_282);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_145);
    }
    } else if (genFeature.isListType()) {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_285);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_280);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_286);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_145);
    } else {
    stringBuffer.append(TEXT_287);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_288);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_145);
    }
    } else {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_171);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_88);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_82);
    }
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_280);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_289);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_225);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_223);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_6);
    } else {
    stringBuffer.append(TEXT_171);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_88);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_82);
    }
    stringBuffer.append(TEXT_290);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_291);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_225);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_223);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_6);
    }
    }
    } else if (genClass.getGetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genClass.getGetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else if (genFeature.hasGetterBody()) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getGetterBody(genModel.getIndentation(stringBuffer)));
    } else if (genFeature.getGetAccessor().equals(INTERFACE_NAME_QUALIFIEDNAME_GETTER)) {
    stringBuffer.append(TEXT_292);
    stringBuffer.append(genModel.getImportedName("de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper"));
    stringBuffer.append(TEXT_293);
    } else {
    stringBuffer.append(TEXT_294);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_295);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_296);
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_297);
    if (genFeature.isMapType()) {
    stringBuffer.append(TEXT_298);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_299);
    } else {
    stringBuffer.append(TEXT_300);
    }
    stringBuffer.append(TEXT_301);
    }
    stringBuffer.append(TEXT_302);
    //Class/getGenFeature.todo.override.javajetinc
    }
    }
    stringBuffer.append(TEXT_175);
    }
    //Class/getGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicGet()) {
    stringBuffer.append(TEXT_303);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_219);
    if (isJDK50) { //Class/basicGetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_304);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_132);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_220);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (genClass.hasStaticFeatures()){
    stringBuffer.append(TEXT_221);
    }
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_305);
    stringBuffer.append(!genFeature.isEffectiveSuppressEMFTypes());
    stringBuffer.append(TEXT_145);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_171);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_88);
    }
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_306);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_223);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_6);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_230);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_231);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_307);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_145);
    } else {
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_6);
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_308);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_280);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_289);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_309);
    } else {
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_283);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_291);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_309);
    }
    } else if (genFeature.hasGetterBody()) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getGetterBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_294);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_295);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_310);
    //Class/basicGetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_175);
    //Class/basicGetGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_311);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_312);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_313);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_314);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_219);
    if (isJDK50) { //Class/basicSetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_153);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_315);
    if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_316);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_317);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_318);
    stringBuffer.append(TEXT_319);
    } else if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_320);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_317);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_318);
    stringBuffer.append(TEXT_319);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_321);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_322);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_323);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_145);
    } else {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_237);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_324);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_325);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_6);
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_326);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_327);
    }
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_329);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_330);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_331);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_333);
    }
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_334);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_335);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_150);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_336);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_261);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_337);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_148);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_338);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_323);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_148);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_340);
    } else {
    stringBuffer.append(TEXT_341);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_342);
    }
    stringBuffer.append(TEXT_145);
    } else {
    stringBuffer.append(TEXT_150);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_336);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_261);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_337);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_148);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_343);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_323);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_145);
    }
    stringBuffer.append(TEXT_344);
    }
    stringBuffer.append(TEXT_319);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_285);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_279);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_280);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_345);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_323);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_318);
    } else {
    stringBuffer.append(TEXT_285);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_346);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_347);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_323);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_318);
    }
    } else {
    stringBuffer.append(TEXT_348);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_295);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_349);
    //Class/basicSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_175);
    //Class/basicSetGenFeature.override.javajetinc
    }
    if (genFeature.isSet() && (isImplementation || !genFeature.isSuppressedSetVisibility())) {
    if (isInterface) { 
    stringBuffer.append(TEXT_350);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_188);
    stringBuffer.append(TEXT_351);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_204);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_188);
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_210);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_211);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_21);
    }
    if (!genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_212);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_21);
    }
    }
    stringBuffer.append(TEXT_352);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_353);
    //Class/setGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_124);
    if (isJDK50) { //Class/setGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) { 
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_354);
    } else { GenOperation setAccessorOperation = genClass.getSetAccessorOperation(genFeature);
    stringBuffer.append(TEXT_158);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_142);
    }
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(setAccessorOperation == null ? "new" + genFeature.getCapName() : setAccessorOperation.getGenParameters().get(0).getName());
    stringBuffer.append(TEXT_355);
    if (CLASS_NAME_REFERENCE_PROPERTY_INSTANCE.equals(genClass.getName()) && genFeature.getAccessorName().equals("Reference")) {
    stringBuffer.append(TEXT_356);
    stringBuffer.append(genModel.getImportedName("de.dlr.sc.virsat.model.dvlm.util.DVLMReferenceCheck"));
    stringBuffer.append(TEXT_357);
    stringBuffer.append(setAccessorOperation == null ? "new" + genFeature.getCapName() : setAccessorOperation.getGenParameters().get(0).getName());
    stringBuffer.append(TEXT_358);
    }
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_359);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (genClass.hasStaticFeatures()){
    stringBuffer.append(TEXT_221);
    }
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_148);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_360);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_88);
    }
    stringBuffer.append(TEXT_361);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_82);
    }
    stringBuffer.append(TEXT_145);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_362);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_148);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_360);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_88);
    }
    stringBuffer.append(TEXT_361);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_82);
    }
    stringBuffer.append(TEXT_145);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_363);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_360);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_88);
    }
    stringBuffer.append(TEXT_361);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_82);
    }
    stringBuffer.append(TEXT_145);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isContainer()) { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_364);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_365);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_366);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_367);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreUtil"));
    stringBuffer.append(TEXT_368);
    stringBuffer.append(genFeature.getEObjectCast());
    stringBuffer.append(TEXT_361);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_369);
    stringBuffer.append(genModel.getImportedName("java.lang.IllegalArgumentException"));
    stringBuffer.append(TEXT_370);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_150);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_371);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_372);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_317);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_373);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_148);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_374);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_361);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_375);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_376);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_261);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_337);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_323);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_323);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_264);
    }
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_145);
    }
    stringBuffer.append(TEXT_364);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_377);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_378);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_379);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_380);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_381);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_382);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_383);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_372);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_317);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_384);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_254);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_381);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_385);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_148);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_386);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_372);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_317);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_373);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_148);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_257);
    }
    stringBuffer.append(TEXT_387);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_361);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_375);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_388);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_389);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_390);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_145);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_389);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_329);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_330);
    }
    stringBuffer.append(TEXT_150);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_331);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_389);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_333);
    }
    stringBuffer.append(TEXT_150);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_334);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_391);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_261);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_337);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_323);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_323);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_392);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_393);
    }
    stringBuffer.append(TEXT_394);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_376);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_261);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_337);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_323);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_323);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_264);
    }
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_237);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_268);
    } else {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_237);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_270);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_271);
    }
    }
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_364);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_395);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_396);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_397);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_398);
    } else {
    stringBuffer.append(TEXT_364);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_399);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_400);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_401);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_402);
    if (isJDK50) {
    stringBuffer.append(TEXT_361);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_80);
    } else {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_403);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_82);
    }
    stringBuffer.append(TEXT_83);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_404);
    }
    } else {
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_237);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_6);
    }
    }
    if (genFeature.isEnumType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_325);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_405);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_406);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_6);
    } else {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_325);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_405);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_406);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_6);
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_361);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_6);
    } else {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_361);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_6);
    }
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_321);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_322);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_145);
    }
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_326);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_327);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_329);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_330);
    }
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_331);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_333);
    }
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_334);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_407);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_261);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_337);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_148);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_408);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_409);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_148);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_361);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_148);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_340);
    } else {
    stringBuffer.append(TEXT_341);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_342);
    }
    stringBuffer.append(TEXT_264);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_407);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_261);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_337);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_148);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_410);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_409);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_148);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_361);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_264);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_411);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_279);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_280);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_412);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_148);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_360);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_88);
    }
    stringBuffer.append(TEXT_361);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_82);
    }
    stringBuffer.append(TEXT_145);
    } else {
    stringBuffer.append(TEXT_411);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_346);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_413);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_148);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_360);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_88);
    }
    stringBuffer.append(TEXT_361);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_82);
    }
    stringBuffer.append(TEXT_145);
    }
    } else if (setAccessorOperation != null) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(setAccessorOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_414);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_295);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_415);
    //Class/setGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_175);
    }
    //Class/setGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicUnset()) {
    stringBuffer.append(TEXT_124);
    if (isJDK50) { //Class/basicUnsetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_416);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_315);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_417);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_82);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_418);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_419);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_318);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_420);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_73);
    }
    stringBuffer.append(TEXT_421);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_145);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_237);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_422);
    }
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_326);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_423);
    }
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_329);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_330);
    }
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_397);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_331);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_333);
    }
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_424);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_425);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_336);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_261);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_426);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_148);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_427);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_428);
    } else {
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_429);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_340);
    } else {
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_342);
    }
    stringBuffer.append(TEXT_430);
    }
    } else {
    stringBuffer.append(TEXT_431);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_295);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_349);
    //Class/basicUnsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_175);
    //Class.basicUnsetGenFeature.override.javajetinc
    }
    if (genFeature.isUnset() && (isImplementation || !genFeature.isSuppressedUnsetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_432);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_188);
    stringBuffer.append(TEXT_433);
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_211);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_352);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_21);
    if (!genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_213);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_82);
    }
    stringBuffer.append(TEXT_217);
    //Class/unsetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_124);
    if (isJDK50) { //Class/unsetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_434);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_162);
    } else {
    stringBuffer.append(TEXT_435);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingUnsetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_142);
    }
    stringBuffer.append(TEXT_132);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_436);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (genClass.hasStaticFeatures()){
    stringBuffer.append(TEXT_221);
    }
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_145);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_437);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_145);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_438);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_145);
    }
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_439);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_440);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_441);
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_145);
    }
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_442);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_443);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_444);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_382);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_254);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_444);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_385);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_148);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_257);
    }
    stringBuffer.append(TEXT_445);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_446);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_389);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_390);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_145);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_389);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_329);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_330);
    }
    stringBuffer.append(TEXT_150);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_397);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_331);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_389);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_333);
    }
    stringBuffer.append(TEXT_150);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_424);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_391);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_261);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_426);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_447);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_393);
    }
    stringBuffer.append(TEXT_394);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_237);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_268);
    } else {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_237);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_270);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_271);
    }
    }
    } else if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_321);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_448);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_145);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_237);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_6);
    }
    }
    if (!genModel.isSuppressNotification()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_326);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_423);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_329);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_330);
    } else {
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_333);
    }
    }
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_422);
    if (!genModel.isVirtualDelegation()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_397);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_331);
    } else {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_424);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_407);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_261);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_426);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_148);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_427);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_428);
    } else {
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_429);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_340);
    } else {
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_342);
    }
    stringBuffer.append(TEXT_264);
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_395);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_396);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_397);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_398);
    } else {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_401);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_402);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_449);
    }
    } else if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_6);
    }
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_397);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_331);
    } else {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_424);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_407);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_261);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_426);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_148);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_427);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_450);
    stringBuffer.append(genFeature.getEDefault());
    } else {
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_148);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_340);
    } else {
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_342);
    }
    stringBuffer.append(TEXT_264);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_411);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_279);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_280);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_451);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_145);
    } else {
    stringBuffer.append(TEXT_411);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_346);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_452);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_145);
    }
    } else if (genClass.getUnsetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genClass.getUnsetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_453);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_295);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_415);
    //Class/unsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_175);
    }
    //Class/unsetGenFeature.override.javajetinc
    }
    if (genFeature.isIsSet() && (isImplementation || !genFeature.isSuppressedIsSetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_454);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_455);
    stringBuffer.append(TEXT_456);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_204);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_455);
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_212);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_352);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_21);
    if (!genFeature.isListType() && genFeature.isChangeable() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_213);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_82);
    }
    stringBuffer.append(TEXT_217);
    //Class/isSetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_124);
    if (isJDK50) { //Class/isSetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_457);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_162);
    } else {
    stringBuffer.append(TEXT_458);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingIsSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_142);
    }
    stringBuffer.append(TEXT_132);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_459);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (genClass.hasStaticFeatures()){
    stringBuffer.append(TEXT_221);
    }
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_145);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_460);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_145);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_461);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_145);
    }
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_462);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_440);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_463);
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_464);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_145);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_330);
    } else {
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_333);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_465);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_279);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_280);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_466);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_145);
    } else {
    stringBuffer.append(TEXT_465);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_346);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_467);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_145);
    }
    } else if (genClass.getIsSetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genClass.getIsSetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_468);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_295);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_469);
    //Class/isSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_175);
    }
    //Class/isSetGenFeature.override.javajetinc
    }
    //Class/genFeature.override.javajetinc
    }//for
    }}.run();
    for (GenOperation genOperation : (isImplementation ? genClass.getImplementedGenOperations() : genClass.getDeclaredGenOperations())) {
    if (isImplementation) {
    if (genOperation.isInvariant() && genOperation.hasInvariantExpression()) {
    stringBuffer.append(TEXT_470);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_471);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_472);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_473);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_474);
    stringBuffer.append(genOperation.getInvariantExpression("\t\t"));
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_7);
    } else if (genOperation.hasInvocationDelegate()) {
    stringBuffer.append(TEXT_475);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_471);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_476);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_473);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_477);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_478);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genOperation.getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_479);
    }
    }
    if (isInterface) {
    stringBuffer.append(TEXT_480);
    stringBuffer.append(TEXT_433);
    if (genOperation.hasDocumentation() || genOperation.hasParameterDocumentation()) {
    stringBuffer.append(TEXT_481);
    if (genOperation.hasDocumentation()) {
    stringBuffer.append(TEXT_482);
    stringBuffer.append(genOperation.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    for (GenParameter genParameter : genOperation.getGenParameters()) {
    if (genParameter.hasDocumentation()) { String documentation = genParameter.getDocumentation("");
    if (documentation.contains("\n") || documentation.contains("\r")) {
    stringBuffer.append(TEXT_483);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_484);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_483);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    }
    }
    stringBuffer.append(TEXT_208);
     /************************************************
        * VirSat specific documentation of return types 
        ************************************************/
    if (genOperation.getTypeParameters(genClass) != null) { 
    stringBuffer.append(TEXT_485);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_486);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_487);
    }
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genOperation.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_214);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_215);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_216);
    }}
    stringBuffer.append(TEXT_217);
    //Class/genOperation.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_124);
    if (isJDK50) { //Class/genOperation.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_161);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genOperation.getParameters(genClass));
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_44);
    } else {
    if (genModel.useGenerics() && !genOperation.hasBody() && !genOperation.isInvariant() && genOperation.hasInvocationDelegate() && genOperation.isUncheckedCast(genClass)) {
    stringBuffer.append(TEXT_70);
    }
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genOperation.getParameters(isImplementation, genClass));
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_488);
    if (genOperation.hasBody()) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else if (genOperation.isInvariant()) {GenClass opClass = genOperation.getGenClass(); String diagnostics = genOperation.getGenParameters().get(0).getName(); String context = genOperation.getGenParameters().get(1).getName();
    if (genOperation.hasInvariantExpression()) {
    stringBuffer.append(TEXT_489);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_490);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_491);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_492);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_493);
    stringBuffer.append(genOperation.getValidationDelegate());
    stringBuffer.append(TEXT_494);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_495);
    stringBuffer.append(genOperation.getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_492);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_496);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_497);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_498);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_188);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_145);
    } else {
    stringBuffer.append(TEXT_499);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_500);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_501);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_502);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_503);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_504);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_188);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_505);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_506);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_507);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
    stringBuffer.append(TEXT_508);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_509);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_510);
    }
    } else if (genOperation.hasInvocationDelegate()) { int size = genOperation.getGenParameters().size();
    stringBuffer.append(TEXT_511);
    if (genOperation.isVoid()) {
    stringBuffer.append(TEXT_150);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_512);
    if (size > 0) {
    stringBuffer.append(TEXT_360);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_513);
    stringBuffer.append(size);
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genOperation.getParametersArray(genClass));
    stringBuffer.append(TEXT_82);
    } else {
    stringBuffer.append(TEXT_514);
    }
    stringBuffer.append(TEXT_145);
    } else {
    stringBuffer.append(TEXT_515);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_88);
    }
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genOperation.getObjectType(genClass));
    stringBuffer.append(TEXT_82);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_512);
    if (size > 0) {
    stringBuffer.append(TEXT_360);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_513);
    stringBuffer.append(size);
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genOperation.getParametersArray(genClass));
    stringBuffer.append(TEXT_82);
    } else {
    stringBuffer.append(TEXT_514);
    }
    stringBuffer.append(TEXT_82);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_223);
    stringBuffer.append(genOperation.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_516);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_517);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.WrappedException"));
    stringBuffer.append(TEXT_518);
    } else {
    stringBuffer.append(TEXT_519);
    //Class/implementedGenOperation.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_175);
    }
    //Class/implementedGenOperation.override.javajetinc
    }//for
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseAddGenFeatures())) {
    stringBuffer.append(TEXT_124);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass)) {
    stringBuffer.append(TEXT_70);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_125);
    }
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_520);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_521);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_522);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_523);
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    stringBuffer.append(TEXT_524);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_525);
    if (genFeature.isListType()) { String cast = "("  + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + (!genModel.useGenerics() ? ")" : "<" + genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject") + ">)(" + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + "<?>)");
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_526);
    stringBuffer.append(cast);
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_527);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_528);
    } else {
    stringBuffer.append(TEXT_529);
    stringBuffer.append(cast);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_530);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_531);
    if (genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_532);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_533);
    } else {
    stringBuffer.append(TEXT_534);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_318);
    }
    } else {
    if (genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_243);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_145);
    } else if (genFeature.isVolatile() || genClass.getImplementingGenModel(genFeature).isDynamicDelegation()) {
    stringBuffer.append(TEXT_243);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_73);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_418);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_234);
    }
    stringBuffer.append(TEXT_535);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_380);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_536);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_382);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_254);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_536);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_385);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_148);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_257);
    }
    stringBuffer.append(TEXT_532);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_533);
    }
    }
    stringBuffer.append(TEXT_394);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_537);
    } else {
    stringBuffer.append(TEXT_538);
    }
    stringBuffer.append(TEXT_175);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseRemoveGenFeatures())) {
    stringBuffer.append(TEXT_124);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_125);
    }
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_539);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_521);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_522);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_523);
    for (GenFeature genFeature : genClass.getEInverseRemoveGenFeatures()) {
    stringBuffer.append(TEXT_524);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_525);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_526);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_308);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_527);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_540);
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_526);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_308);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_541);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_542);
    } else {
    stringBuffer.append(TEXT_526);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_543);
    }
    } else if (genFeature.isContainer() && !genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_544);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_318);
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_545);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_546);
    } else {
    stringBuffer.append(TEXT_532);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_547);
    }
    }
    stringBuffer.append(TEXT_394);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_548);
    } else {
    stringBuffer.append(TEXT_549);
    }
    stringBuffer.append(TEXT_175);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEBasicRemoveFromContainerGenFeatures())) {
    stringBuffer.append(TEXT_124);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_125);
    }
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_550);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_551);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_523);
    for (GenFeature genFeature : genClass.getEBasicRemoveFromContainerGenFeatures()) {
    GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_524);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_552);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_148);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_257);
    }
    stringBuffer.append(TEXT_394);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_553);
    } else {
    stringBuffer.append(TEXT_554);
    }
    stringBuffer.append(TEXT_175);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEGetGenFeatures())) {
    stringBuffer.append(TEXT_124);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_125);
    }
    stringBuffer.append(TEXT_555);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_523);
    for (GenFeature genFeature : genClass.getEGetGenFeatures()) {
    stringBuffer.append(TEXT_524);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_525);
    if (genFeature.isPrimitiveType()) {
    if (isJDK50) {
    stringBuffer.append(TEXT_556);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_234);
    } else if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_556);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_557);
    } else {
    stringBuffer.append(TEXT_558);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_559);
    }
    } else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_560);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_561);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_234);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_562);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_527);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_563);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_234);
    } else {
    stringBuffer.append(TEXT_564);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_565);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_566);
    }
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_562);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_541);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_567);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_234);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_564);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_568);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_569);
    } else {
    stringBuffer.append(TEXT_556);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_234);
    }
    }
    stringBuffer.append(TEXT_394);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_570);
    } else {
    stringBuffer.append(TEXT_571);
    }
    stringBuffer.append(TEXT_175);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getESetGenFeatures())) {
    stringBuffer.append(TEXT_124);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass) && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_70);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_125);
    }
    stringBuffer.append(TEXT_572);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_523);
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    stringBuffer.append(TEXT_524);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_525);
    if (genFeature.isListType()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_573);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_279);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_541);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_574);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_573);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_575);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_573);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_576);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_527);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_577);
    } else {
    stringBuffer.append(TEXT_573);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_578);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_575);
    }
    } else {
    stringBuffer.append(TEXT_243);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_579);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_580);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    if (isJDK50) {
    stringBuffer.append(TEXT_581);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_582);
    }
    stringBuffer.append(TEXT_583);
    }
    } else if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_584);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_585);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_586);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_559);
    } else {
    stringBuffer.append(TEXT_584);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_88);
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType() || !genFeature.getRawType().equals(genFeature.getType(genClass))) {
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_82);
    }
    stringBuffer.append(TEXT_587);
    }
    stringBuffer.append(TEXT_588);
    }
    stringBuffer.append(TEXT_394);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_589);
    } else {
    stringBuffer.append(TEXT_590);
    }
    stringBuffer.append(TEXT_175);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEUnsetGenFeatures())) {
    stringBuffer.append(TEXT_124);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_125);
    }
    stringBuffer.append(TEXT_591);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_523);
    for (GenFeature genFeature : genClass.getEUnsetGenFeatures()) {
    stringBuffer.append(TEXT_524);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_525);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_573);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_541);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_592);
    } else {
    stringBuffer.append(TEXT_243);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_593);
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_594);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_234);
    } else if (!genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_584);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_595);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_243);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_438);
    } else {
    stringBuffer.append(TEXT_584);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_145);
    }
    stringBuffer.append(TEXT_588);
    }
    stringBuffer.append(TEXT_394);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_596);
    } else {
    stringBuffer.append(TEXT_597);
    }
    stringBuffer.append(TEXT_175);
    //Class/eUnset.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEIsSetGenFeatures())) {
    stringBuffer.append(TEXT_124);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) {
    if (genFeature.isListType() && !genFeature.isUnsettable() && !genFeature.isWrappedFeatureMapType() && !genClass.isField(genFeature) && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_70);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_125);
    }
    stringBuffer.append(TEXT_598);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_523);
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) { String safeNameAccessor = genFeature.getSafeName(); if ("featureID".equals(safeNameAccessor)) { safeNameAccessor = "this." + safeNameAccessor; }
    stringBuffer.append(TEXT_524);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_525);
    if (genFeature.hasSettingDelegate()) {
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_599);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_234);
    } else {
    stringBuffer.append(TEXT_556);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_461);
    }
    } else if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_600);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_541);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_601);
    } else {
    stringBuffer.append(TEXT_556);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_602);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_603);
    }
    } else {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_556);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_602);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_604);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_243);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_605);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_602);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_604);
    } else {
    stringBuffer.append(TEXT_606);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_607);
    }
    }
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_599);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_234);
    } else if (genFeature.isResolveProxies()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_556);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_608);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_609);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_610);
    } else {
    stringBuffer.append(TEXT_611);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_612);
    }
    }
    } else if (!genFeature.hasEDefault()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_556);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_608);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_609);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_610);
    } else {
    stringBuffer.append(TEXT_556);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_612);
    }
    }
    } else if (genFeature.isPrimitiveType() || genFeature.isEnumType()) {
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_526);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_613);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_6);
    } else {
    stringBuffer.append(TEXT_529);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_614);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_449);
    }
    } else {
    stringBuffer.append(TEXT_556);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_377);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_6);
    }
    } else {
    if (genFeature.isEnumType() && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_609);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_615);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_6);
    } else {
    stringBuffer.append(TEXT_556);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_616);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_6);
    }
    }
    } else {//datatype
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_556);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_405);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_617);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_618);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_145);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_243);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_605);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_405);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_617);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_618);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_145);
    } else {
    stringBuffer.append(TEXT_556);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_405);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_619);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_618);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_559);
    }
    }
    }
    }
    stringBuffer.append(TEXT_394);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_620);
    } else {
    stringBuffer.append(TEXT_621);
    }
    stringBuffer.append(TEXT_175);
    //Class/eIsSet.override.javajetinc
    }
    if (isImplementation && (!genClass.getMixinGenFeatures().isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty())) {
    if (!genClass.getMixinGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_124);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_125);
    }
    stringBuffer.append(TEXT_622);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_623);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_624);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_625);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_242);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_626);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_627);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_628);
    }
    stringBuffer.append(TEXT_629);
    }
    stringBuffer.append(TEXT_630);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_125);
    }
    stringBuffer.append(TEXT_631);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_623);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_624);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_632);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_626);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_627);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_628);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_624);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_633);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_242);
    for (GenFeature genFeature : genClass.getGenFeatures()) {
    stringBuffer.append(TEXT_626);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_627);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_628);
    }
    stringBuffer.append(TEXT_634);
    }
    if (genModel.isOperationReflection() && isImplementation && (!genClass.getMixinGenOperations().isEmpty() || !genClass.getOverrideGenOperations(genClass.getExtendedGenOperations(), genClass.getImplementedGenOperations()).isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty())) {
    stringBuffer.append(TEXT_124);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_125);
    }
    stringBuffer.append(TEXT_635);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_623);
    for (GenClass extendedGenClass : genClass.getExtendedGenClasses()) { List<GenOperation> extendedImplementedGenOperations = extendedGenClass.getImplementedGenOperations(); List<GenOperation> implementedGenOperations = genClass.getImplementedGenOperations();
    if (!genClass.getOverrideGenOperations(extendedImplementedGenOperations, implementedGenOperations).isEmpty()) {
    stringBuffer.append(TEXT_624);
    stringBuffer.append(extendedGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_636);
    for (GenOperation genOperation : extendedImplementedGenOperations) { GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    if (implementedGenOperations.contains(overrideGenOperation)) {
    stringBuffer.append(TEXT_626);
    stringBuffer.append(extendedGenClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_627);
    stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_6);
    }
    }
    stringBuffer.append(TEXT_637);
    }
    }
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_624);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_636);
    for (GenOperation genOperation : mixinGenClass.getGenOperations()) { GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    stringBuffer.append(TEXT_626);
    stringBuffer.append(mixinGenClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_627);
    stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation != null ? overrideGenOperation : genOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_628);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_624);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_638);
    stringBuffer.append(negativeOperationOffsetCorrection);
    stringBuffer.append(TEXT_242);
    for (GenOperation genOperation : genClass.getGenOperations()) {
    stringBuffer.append(TEXT_626);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_627);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_628);
    }
    stringBuffer.append(TEXT_639);
    }
    if (isImplementation && genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_124);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_125);
    }
    stringBuffer.append(TEXT_640);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_641);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_125);
    }
    stringBuffer.append(TEXT_642);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_643);
    }
    { List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
    if (!eVirtualIndexBitFields.isEmpty()) { List<String> allEVirtualIndexBitFields = genClass.getAllEVirtualIndexBitFields(new ArrayList<String>());
    stringBuffer.append(TEXT_124);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_125);
    }
    stringBuffer.append(TEXT_644);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_524);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_645);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_646);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_125);
    }
    stringBuffer.append(TEXT_647);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_524);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_648);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_649);
    }
    stringBuffer.append(TEXT_650);
    }
    }
    }
    if (genModel.isOperationReflection() && isImplementation && !genClass.getImplementedGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_124);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_125);
    }
    if (genModel.useGenerics()) {
    boolean isUnchecked = false; boolean isRaw = false; LOOP: for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenOperations() : genClass.getAllGenOperations())) { for (GenParameter genParameter : genOperation.getGenParameters()) { if (genParameter.isUncheckedCast()) { if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType()) { isUnchecked = true; } if (genParameter.usesOperationTypeParameters() && !genParameter.getEcoreParameter().getEGenericType().getETypeArguments().isEmpty()) { isRaw = true; break LOOP; }}}}
    if (isUnchecked) {
    stringBuffer.append(TEXT_651);
    if (!isRaw) {
    stringBuffer.append(TEXT_652);
    } else {
    stringBuffer.append(TEXT_653);
    }
    stringBuffer.append(TEXT_82);
    }
    }
    stringBuffer.append(TEXT_654);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_655);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_656);
    stringBuffer.append(negativeOperationOffsetCorrection);
    stringBuffer.append(TEXT_523);
    for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenOperations() : genClass.getAllGenOperations())) { List<GenParameter> genParameters = genOperation.getGenParameters(); int size = genParameters.size();  boolean hasCheckedException = genOperation.hasCheckedException(); String indent = hasCheckedException ? "\t" : ""; GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    stringBuffer.append(TEXT_524);
    stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation != null ? overrideGenOperation : genOperation));
    stringBuffer.append(TEXT_525);
    if (hasCheckedException) {
    stringBuffer.append(TEXT_657);
    /*}*/}
    if (genOperation.isVoid()) {
    stringBuffer.append(TEXT_243);
    stringBuffer.append(indent);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_88);
    for (int i = 0; i < size; i++) { GenParameter genParameter = genParameters.get(i);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_88);
    }
    if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType() || !genParameter.usesOperationTypeParameters() && !genParameter.getRawType().equals(genParameter.getType(genClass))) {
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genParameter.usesOperationTypeParameters() ? genParameter.getRawImportedType() : genParameter.getObjectType(genClass));
    stringBuffer.append(TEXT_82);
    }
    stringBuffer.append(TEXT_658);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_82);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_223);
    stringBuffer.append(genParameter.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_21);
    }
    if (i < (size - 1)) {
    stringBuffer.append(TEXT_148);
    }
    }
    stringBuffer.append(TEXT_659);
    stringBuffer.append(indent);
    stringBuffer.append(TEXT_660);
    } else {
    stringBuffer.append(TEXT_243);
    stringBuffer.append(indent);
    stringBuffer.append(TEXT_661);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_360);
    stringBuffer.append(genOperation.getObjectType(genClass));
    stringBuffer.append(TEXT_88);
    }
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_88);
    for (int i = 0; i < size; i++) { GenParameter genParameter = genParameters.get(i);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_88);
    }
    if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType() || !genParameter.usesOperationTypeParameters() && !genParameter.getRawType().equals(genParameter.getType(genClass))) {
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genParameter.usesOperationTypeParameters() ? genParameter.getRawImportedType() : genParameter.getObjectType(genClass));
    stringBuffer.append(TEXT_82);
    }
    stringBuffer.append(TEXT_658);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_82);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_223);
    stringBuffer.append(genParameter.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_21);
    }
    if (i < (size - 1)) {
    stringBuffer.append(TEXT_148);
    }
    }
    stringBuffer.append(TEXT_82);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_82);
    }
    stringBuffer.append(TEXT_6);
    }
    if (hasCheckedException) {/*{*/
    stringBuffer.append(TEXT_662);
    stringBuffer.append(genModel.getImportedName("java.lang.Throwable"));
    stringBuffer.append(TEXT_663);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_664);
    }
    }
    stringBuffer.append(TEXT_394);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_665);
    } else {
    stringBuffer.append(TEXT_666);
    }
    stringBuffer.append(TEXT_175);
    }
    if (!genClass.hasImplementedToStringGenOperation() && isImplementation && !genModel.isReflectiveDelegation() && !genModel.isDynamicDelegation() && !genClass.getToStringGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_124);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_125);
    }
    stringBuffer.append(TEXT_667);
    { boolean first = true;
    for (GenFeature genFeature : genClass.getToStringGenFeatures()) {
    if (first) { first = false;
    stringBuffer.append(TEXT_668);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_669);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_670);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_669);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.isUnsettable() && !genFeature.isListType()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_671);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_672);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_673);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_136);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_674);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_342);
    }
    stringBuffer.append(TEXT_675);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_676);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_136);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_674);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_342);
    }
    stringBuffer.append(TEXT_677);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_270);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_678);
    stringBuffer.append(genModel.getNonNLS());
    }
    } else {
    stringBuffer.append(TEXT_136);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_674);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_342);
    }
    stringBuffer.append(TEXT_677);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_679);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_680);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (!genFeature.isListType() && !genFeature.isReferenceType()){
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_264);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_681);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_682);
    } else {
    stringBuffer.append(TEXT_683);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_270);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_684);
    }
    } else {
    stringBuffer.append(TEXT_683);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_145);
    }
    }
    }
    }
    }
    stringBuffer.append(TEXT_685);
    }
    if (isImplementation && genClass.isMapEntry()) { GenFeature keyFeature = genClass.getMapEntryKeyFeature(); GenFeature valueFeature = genClass.getMapEntryValueFeature();
    String objectType = genModel.getImportedName("java.lang.Object");
    String keyType = isJDK50 ? keyFeature.getObjectType(genClass) : objectType;
    String valueType = isJDK50 ? valueFeature.getObjectType(genClass) : objectType;
    String eMapType = genModel.getImportedName("org.eclipse.emf.common.util.EMap") + (isJDK50 ? "<" + keyType + ", " + valueType + ">" : "");
    stringBuffer.append(TEXT_124);
    if (isGWT) {
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_686);
    stringBuffer.append(objectType);
    stringBuffer.append(TEXT_687);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_688);
    if (!isJDK50 && keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_277);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_689);
    } else {
    stringBuffer.append(TEXT_690);
    }
    stringBuffer.append(TEXT_691);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_692);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_693);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_82);
    }
    stringBuffer.append(TEXT_694);
    } else if (isJDK50) {
    stringBuffer.append(TEXT_695);
    } else if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_696);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_697);
    stringBuffer.append(keyFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_559);
    } else {
    stringBuffer.append(TEXT_698);
    stringBuffer.append(keyFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_699);
    }
    stringBuffer.append(TEXT_700);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_701);
    if (!isJDK50 && valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_277);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_702);
    } else {
    stringBuffer.append(TEXT_703);
    }
    stringBuffer.append(TEXT_700);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_704);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_705);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_706);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_707);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_82);
    }
    stringBuffer.append(TEXT_708);
    } else if (isJDK50) {
    stringBuffer.append(TEXT_709);
    } else if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_710);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_711);
    stringBuffer.append(valueFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_559);
    } else {
    stringBuffer.append(TEXT_712);
    stringBuffer.append(valueFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_713);
    }
    stringBuffer.append(TEXT_714);
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_70);
    }
    stringBuffer.append(TEXT_131);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_715);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_716);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_717);
    }
    stringBuffer.append(TEXT_718);
    stringBuffer.append(isInterface ? " " + genClass.getInterfaceName() : genClass.getClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_7);
    return stringBuffer.toString();
  }
}
