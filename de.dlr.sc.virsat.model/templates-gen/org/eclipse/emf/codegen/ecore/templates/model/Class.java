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
  protected final String TEXT_7 = NL + "package ";
  protected final String TEXT_8 = ";";
  protected final String TEXT_9 = NL;
  protected final String TEXT_10 = NL;
  protected final String TEXT_11 = NL;
  protected final String TEXT_12 = NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * A representation of the model object '<em><b>";
  protected final String TEXT_13 = "</b></em>'." + NL + " * <!-- end-user-doc -->";
  protected final String TEXT_14 = NL + " *" + NL + " * <!-- begin-model-doc -->" + NL + " * ";
  protected final String TEXT_15 = NL + " * <!-- end-model-doc -->";
  protected final String TEXT_16 = NL + " *";
  protected final String TEXT_17 = NL + " * <p>" + NL + " * The following features are supported:" + NL + " * </p>" + NL + " * <ul>";
  protected final String TEXT_18 = NL + " *   <li>{@link ";
  protected final String TEXT_19 = "#";
  protected final String TEXT_20 = " <em>";
  protected final String TEXT_21 = "</em>}</li>";
  protected final String TEXT_22 = NL + " * </ul>";
  protected final String TEXT_23 = NL + " *";
  protected final String TEXT_24 = NL + " * @see ";
  protected final String TEXT_25 = "#get";
  protected final String TEXT_26 = "()";
  protected final String TEXT_27 = NL + " * @model ";
  protected final String TEXT_28 = NL + " *        ";
  protected final String TEXT_29 = NL + " * @model";
  protected final String TEXT_30 = NL + " * @extends ";
  protected final String TEXT_31 = NL + " * @generated" + NL + " */";
  protected final String TEXT_32 = NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * An implementation of the model object '<em><b>";
  protected final String TEXT_33 = "</b></em>'." + NL + " * <!-- end-user-doc -->";
  protected final String TEXT_34 = NL + " * <p>" + NL + " * The following features are implemented:" + NL + " * </p>" + NL + " * <ul>";
  protected final String TEXT_35 = NL + " *   <li>{@link ";
  protected final String TEXT_36 = "#";
  protected final String TEXT_37 = " <em>";
  protected final String TEXT_38 = "</em>}</li>";
  protected final String TEXT_39 = NL + " * </ul>";
  protected final String TEXT_40 = NL + " *" + NL + " * @generated" + NL + " */";
  protected final String TEXT_41 = NL + "public";
  protected final String TEXT_42 = " abstract";
  protected final String TEXT_43 = " class ";
  protected final String TEXT_44 = NL + "public interface ";
  protected final String TEXT_45 = NL + "{";
  protected final String TEXT_46 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_47 = " copyright = ";
  protected final String TEXT_48 = ";";
  protected final String TEXT_49 = NL;
  protected final String TEXT_50 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_51 = " mofDriverNumber = \"";
  protected final String TEXT_52 = "\";";
  protected final String TEXT_53 = NL;
  protected final String TEXT_54 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final long serialVersionUID = 1L;" + NL;
  protected final String TEXT_55 = NL + "\t/**" + NL + "\t * An array of objects representing the values of non-primitive features." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_56 = NL + "\t@";
  protected final String TEXT_57 = NL + "\tprotected Object[] ";
  protected final String TEXT_58 = ";" + NL;
  protected final String TEXT_59 = NL + "\t/**" + NL + "\t * A bit field representing the indices of non-primitive feature values." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_60 = NL + "\t@";
  protected final String TEXT_61 = NL + "\tprotected int ";
  protected final String TEXT_62 = ";" + NL;
  protected final String TEXT_63 = NL + "\t/**" + NL + "\t * A set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_64 = NL + "\t@";
  protected final String TEXT_65 = NL + "\tprotected int ";
  protected final String TEXT_66 = " = 0;" + NL;
  protected final String TEXT_67 = NL + "\t/**" + NL + "\t * The empty value for the '{@link #";
  protected final String TEXT_68 = "() <em>";
  protected final String TEXT_69 = "</em>}' array accessor." + NL + "\t * This is specialized for the more specific element type known in this context." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_70 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_71 = NL + "\t@SuppressWarnings(\"rawtypes\")";
  protected final String TEXT_72 = NL + "\tprotected static final ";
  protected final String TEXT_73 = "[] ";
  protected final String TEXT_74 = "_EEMPTY_ARRAY = new ";
  protected final String TEXT_75 = " [0]";
  protected final String TEXT_76 = ";" + NL;
  protected final String TEXT_77 = NL + "\t/**" + NL + "\t * The cached setting delegate for the '{@link #";
  protected final String TEXT_78 = "() <em>";
  protected final String TEXT_79 = "</em>}' ";
  protected final String TEXT_80 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_81 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_82 = NL + "\t@";
  protected final String TEXT_83 = NL + "\tprotected ";
  protected final String TEXT_84 = ".Internal.SettingDelegate ";
  protected final String TEXT_85 = "__ESETTING_DELEGATE = ((";
  protected final String TEXT_86 = ".Internal)";
  protected final String TEXT_87 = ").getSettingDelegate();" + NL;
  protected final String TEXT_88 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_89 = "() <em>";
  protected final String TEXT_90 = "</em>}' ";
  protected final String TEXT_91 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_92 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_93 = NL + "\t@";
  protected final String TEXT_94 = NL + "\tprotected ";
  protected final String TEXT_95 = " ";
  protected final String TEXT_96 = ";" + NL;
  protected final String TEXT_97 = NL + "\t/**" + NL + "\t * The empty value for the '{@link #";
  protected final String TEXT_98 = "() <em>";
  protected final String TEXT_99 = "</em>}' array accessor." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_100 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_101 = NL + "\t@SuppressWarnings(\"rawtypes\")";
  protected final String TEXT_102 = NL + "\tprotected static final ";
  protected final String TEXT_103 = "[] ";
  protected final String TEXT_104 = "_EEMPTY_ARRAY = new ";
  protected final String TEXT_105 = " [0]";
  protected final String TEXT_106 = ";" + NL;
  protected final String TEXT_107 = NL + "\t/**" + NL + "\t * The default value of the '{@link #";
  protected final String TEXT_108 = "() <em>";
  protected final String TEXT_109 = "</em>}' ";
  protected final String TEXT_110 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_111 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_112 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_113 = NL + "\tprotected static final ";
  protected final String TEXT_114 = " ";
  protected final String TEXT_115 = "; // TODO The default value literal \"";
  protected final String TEXT_116 = "\" is not valid.";
  protected final String TEXT_117 = " = ";
  protected final String TEXT_118 = ";";
  protected final String TEXT_119 = NL;
  protected final String TEXT_120 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_121 = NL + "\t@";
  protected final String TEXT_122 = NL + "\tprotected int ";
  protected final String TEXT_123 = " = 0;" + NL;
  protected final String TEXT_124 = NL + "\t/**" + NL + "\t * The offset of the flags representing the value of the '{@link #";
  protected final String TEXT_125 = "() <em>";
  protected final String TEXT_126 = "</em>}' ";
  protected final String TEXT_127 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_128 = "_EFLAG_OFFSET = ";
  protected final String TEXT_129 = ";" + NL + "" + NL + "\t/**" + NL + "\t * The flags representing the default value of the '{@link #";
  protected final String TEXT_130 = "() <em>";
  protected final String TEXT_131 = "</em>}' ";
  protected final String TEXT_132 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_133 = "_EFLAG_DEFAULT = ";
  protected final String TEXT_134 = ".ordinal()";
  protected final String TEXT_135 = ".VALUES.indexOf(";
  protected final String TEXT_136 = ")";
  protected final String TEXT_137 = " << ";
  protected final String TEXT_138 = "_EFLAG_OFFSET;" + NL + "" + NL + "\t/**" + NL + "\t * The array of enumeration values for '{@link ";
  protected final String TEXT_139 = " ";
  protected final String TEXT_140 = "}'" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprivate static final ";
  protected final String TEXT_141 = "[] ";
  protected final String TEXT_142 = "_EFLAG_VALUES = ";
  protected final String TEXT_143 = ".values()";
  protected final String TEXT_144 = "(";
  protected final String TEXT_145 = "[])";
  protected final String TEXT_146 = ".VALUES.toArray(new ";
  protected final String TEXT_147 = "[";
  protected final String TEXT_148 = ".VALUES.size()])";
  protected final String TEXT_149 = ";" + NL;
  protected final String TEXT_150 = NL + "\t/**" + NL + "\t * The flag";
  protected final String TEXT_151 = " representing the value of the '{@link #";
  protected final String TEXT_152 = "() <em>";
  protected final String TEXT_153 = "</em>}' ";
  protected final String TEXT_154 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_155 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_156 = "_EFLAG = ";
  protected final String TEXT_157 = " << ";
  protected final String TEXT_158 = "_EFLAG_OFFSET";
  protected final String TEXT_159 = ";" + NL;
  protected final String TEXT_160 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_161 = "() <em>";
  protected final String TEXT_162 = "</em>}' ";
  protected final String TEXT_163 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_164 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_165 = NL + "\t@";
  protected final String TEXT_166 = NL + "\tprotected ";
  protected final String TEXT_167 = " ";
  protected final String TEXT_168 = " = ";
  protected final String TEXT_169 = ";" + NL;
  protected final String TEXT_170 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_171 = NL + "\t@";
  protected final String TEXT_172 = NL + "\tprotected int ";
  protected final String TEXT_173 = " = 0;" + NL;
  protected final String TEXT_174 = NL + "\t/**" + NL + "\t * The flag representing whether the ";
  protected final String TEXT_175 = " ";
  protected final String TEXT_176 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_177 = "_ESETFLAG = 1 << ";
  protected final String TEXT_178 = ";" + NL;
  protected final String TEXT_179 = NL + "\t/**" + NL + "\t * This is true if the ";
  protected final String TEXT_180 = " ";
  protected final String TEXT_181 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_182 = NL + "\t@";
  protected final String TEXT_183 = NL + "\tprotected boolean ";
  protected final String TEXT_184 = "ESet;" + NL;
  protected final String TEXT_185 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int ";
  protected final String TEXT_186 = " = ";
  protected final String TEXT_187 = ".getFeatureID(";
  protected final String TEXT_188 = ") - ";
  protected final String TEXT_189 = ";" + NL;
  protected final String TEXT_190 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int ";
  protected final String TEXT_191 = " = ";
  protected final String TEXT_192 = ".getFeatureID(";
  protected final String TEXT_193 = ") - ";
  protected final String TEXT_194 = ";" + NL;
  protected final String TEXT_195 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int \"EOPERATION_OFFSET_CORRECTION\" = ";
  protected final String TEXT_196 = ".getOperationID(";
  protected final String TEXT_197 = ") - ";
  protected final String TEXT_198 = ";" + NL;
  protected final String TEXT_199 = NL + "\t/**" + NL + "\t * *********************************" + NL + "\t * VirSat Specific Code Generation" + NL + "\t * *********************************" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_200 = "public";
  protected final String TEXT_201 = "protected";
  protected final String TEXT_202 = " ";
  protected final String TEXT_203 = "()" + NL + "\t{" + NL + "\t\tsuper();" + NL + "\t\t";
  protected final String TEXT_204 = "this.uuid = new de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid(); ";
  protected final String TEXT_205 = NL + "\t\t";
  protected final String TEXT_206 = " |= ";
  protected final String TEXT_207 = "_EFLAG";
  protected final String TEXT_208 = "_DEFAULT";
  protected final String TEXT_209 = ";";
  protected final String TEXT_210 = NL + "\t}" + NL + "\t" + NL + "\t";
  protected final String TEXT_211 = NL + "\t/**" + NL + "\t * *********************************" + NL + "\t * VirSat Specific Code Generation" + NL + "\t * *********************************" + NL + "\t * This method handles unresolved references into otehr resources." + NL + "\t * When ever a proxy object in the DVLM model is resolved" + NL + "\t * This method will check the object and place an error to the resource" + NL + "\t * if it cannot be resolved " + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Override" + NL + "\tpublic ";
  protected final String TEXT_212 = " eResolveProxy(";
  protected final String TEXT_213 = " proxy) {" + NL + "\t\tEObject resolvedProxy = super.eResolveProxy(proxy);" + NL + "\t\treturn ";
  protected final String TEXT_214 = ".checkProxyObject(resolvedProxy, proxy, this.eResource());" + NL + "\t}" + NL + "\t";
  protected final String TEXT_215 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_216 = NL + "\t@Override";
  protected final String TEXT_217 = NL + "\tprotected ";
  protected final String TEXT_218 = " eStaticClass()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_219 = ";" + NL + "\t}" + NL;
  protected final String TEXT_220 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected static final int ESTATIC_FEATURE_COUNT = ";
  protected final String TEXT_221 = ";" + NL;
  protected final String TEXT_222 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_223 = NL + "\t@Override";
  protected final String TEXT_224 = NL + "\tprotected int eStaticFeatureCount()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_225 = ";" + NL + "\t}" + NL;
  protected final String TEXT_226 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * This is specialized for the more specific element type known in this context." + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_227 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_228 = NL + "\t@Override";
  protected final String TEXT_229 = NL + "\tpublic ";
  protected final String TEXT_230 = "[] ";
  protected final String TEXT_231 = "()" + NL + "\t{";
  protected final String TEXT_232 = NL + "\t\t";
  protected final String TEXT_233 = " list = (";
  protected final String TEXT_234 = ")";
  protected final String TEXT_235 = "();" + NL + "\t\tif (list.isEmpty()) return ";
  protected final String TEXT_236 = "_EEMPTY_ARRAY;";
  protected final String TEXT_237 = NL + "\t\tif (";
  protected final String TEXT_238 = " == null || ";
  protected final String TEXT_239 = ".isEmpty()) return ";
  protected final String TEXT_240 = "_EEMPTY_ARRAY;" + NL + "\t\t";
  protected final String TEXT_241 = " list = (";
  protected final String TEXT_242 = ")";
  protected final String TEXT_243 = ";";
  protected final String TEXT_244 = NL + "\t\tlist.shrink();" + NL + "\t\treturn (";
  protected final String TEXT_245 = "[])list.data();" + NL + "\t}" + NL;
  protected final String TEXT_246 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * This is specialized for the more specific element type known in this context." + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_247 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_248 = NL + "\t@Override";
  protected final String TEXT_249 = NL + "\tpublic ";
  protected final String TEXT_250 = " ";
  protected final String TEXT_251 = "_";
  protected final String TEXT_252 = "()" + NL + "\t{";
  protected final String TEXT_253 = NL + "\t\t";
  protected final String TEXT_254 = " ";
  protected final String TEXT_255 = " = (";
  protected final String TEXT_256 = ")eVirtualGet(";
  protected final String TEXT_257 = ");";
  protected final String TEXT_258 = NL + "\t\tif (";
  protected final String TEXT_259 = " == null)" + NL + "\t\t{";
  protected final String TEXT_260 = NL + "\t\t\teVirtualSet(";
  protected final String TEXT_261 = ", ";
  protected final String TEXT_262 = " = new ";
  protected final String TEXT_263 = ");";
  protected final String TEXT_264 = NL + "\t\t\t";
  protected final String TEXT_265 = " = new ";
  protected final String TEXT_266 = ";";
  protected final String TEXT_267 = NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_268 = ";" + NL + "\t}" + NL;
  protected final String TEXT_269 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * This is specialized for the more specific type known in this context." + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_270 = NL + "\t@Override";
  protected final String TEXT_271 = NL + "\tpublic ";
  protected final String TEXT_272 = " basicSet";
  protected final String TEXT_273 = "(";
  protected final String TEXT_274 = " new";
  protected final String TEXT_275 = ", ";
  protected final String TEXT_276 = " msgs)" + NL + "\t{" + NL + "\t\treturn super.basicSet";
  protected final String TEXT_277 = "(new";
  protected final String TEXT_278 = ", msgs);" + NL + "\t}" + NL;
  protected final String TEXT_279 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * This is specialized for the more specific type known in this context." + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_280 = NL + "\t@Override";
  protected final String TEXT_281 = NL + "\tpublic void set";
  protected final String TEXT_282 = "_";
  protected final String TEXT_283 = "(";
  protected final String TEXT_284 = " ";
  protected final String TEXT_285 = ")" + NL + "\t{" + NL + "\t\tsuper.set";
  protected final String TEXT_286 = "_";
  protected final String TEXT_287 = "(";
  protected final String TEXT_288 = ");" + NL + "\t}" + NL;
  protected final String TEXT_289 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_290 = NL + "\t";
  protected final String TEXT_291 = "[] ";
  protected final String TEXT_292 = "();" + NL;
  protected final String TEXT_293 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_294 = NL + "\tpublic ";
  protected final String TEXT_295 = "[] ";
  protected final String TEXT_296 = "()" + NL + "\t{";
  protected final String TEXT_297 = NL + "\t\t";
  protected final String TEXT_298 = " list = (";
  protected final String TEXT_299 = ")";
  protected final String TEXT_300 = "();" + NL + "\t\tif (list.isEmpty()) return ";
  protected final String TEXT_301 = "_EEMPTY_ARRAY;";
  protected final String TEXT_302 = NL + "\t\tif (";
  protected final String TEXT_303 = " == null || ";
  protected final String TEXT_304 = ".isEmpty()) return ";
  protected final String TEXT_305 = "_EEMPTY_ARRAY;" + NL + "\t\t";
  protected final String TEXT_306 = " list = (";
  protected final String TEXT_307 = ")";
  protected final String TEXT_308 = ";";
  protected final String TEXT_309 = NL + "\t\tlist.shrink();" + NL + "\t\treturn (";
  protected final String TEXT_310 = "[])list.data();" + NL + "\t}" + NL;
  protected final String TEXT_311 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_312 = NL + "\t";
  protected final String TEXT_313 = " get";
  protected final String TEXT_314 = "(int index);" + NL;
  protected final String TEXT_315 = NL + "\tpublic ";
  protected final String TEXT_316 = " get";
  protected final String TEXT_317 = "(int index)" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_318 = "(";
  protected final String TEXT_319 = ")";
  protected final String TEXT_320 = "().get(index);" + NL + "\t}" + NL;
  protected final String TEXT_321 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_322 = NL + "\tint get";
  protected final String TEXT_323 = "Length();" + NL;
  protected final String TEXT_324 = NL + "\tpublic int get";
  protected final String TEXT_325 = "Length()" + NL + "\t{";
  protected final String TEXT_326 = NL + "\t\treturn ";
  protected final String TEXT_327 = "().size();";
  protected final String TEXT_328 = NL + "\t\treturn ";
  protected final String TEXT_329 = " == null ? 0 : ";
  protected final String TEXT_330 = ".size();";
  protected final String TEXT_331 = NL + "\t}" + NL;
  protected final String TEXT_332 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_333 = NL + "\tvoid set";
  protected final String TEXT_334 = "(";
  protected final String TEXT_335 = "[] new";
  protected final String TEXT_336 = ");" + NL;
  protected final String TEXT_337 = NL + "\tpublic void set";
  protected final String TEXT_338 = "(";
  protected final String TEXT_339 = "[] new";
  protected final String TEXT_340 = ")" + NL + "\t{" + NL + "\t\t((";
  protected final String TEXT_341 = ")";
  protected final String TEXT_342 = "()).setData(new";
  protected final String TEXT_343 = ".length, new";
  protected final String TEXT_344 = ");" + NL + "\t}" + NL;
  protected final String TEXT_345 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_346 = NL + "\tvoid set";
  protected final String TEXT_347 = "(int index, ";
  protected final String TEXT_348 = " element);" + NL;
  protected final String TEXT_349 = NL + "\tpublic void set";
  protected final String TEXT_350 = "(int index, ";
  protected final String TEXT_351 = " element)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_352 = "().set(index, element);" + NL + "\t}" + NL;
  protected final String TEXT_353 = NL + "\t/**" + NL + "\t * Returns the value of the '<em><b>";
  protected final String TEXT_354 = "</b></em>' ";
  protected final String TEXT_355 = ".";
  protected final String TEXT_356 = NL + "\t * The key is of type ";
  protected final String TEXT_357 = "list of {@link ";
  protected final String TEXT_358 = "}";
  protected final String TEXT_359 = "{@link ";
  protected final String TEXT_360 = "}";
  protected final String TEXT_361 = "," + NL + "\t * and the value is of type ";
  protected final String TEXT_362 = "list of {@link ";
  protected final String TEXT_363 = "}";
  protected final String TEXT_364 = "{@link ";
  protected final String TEXT_365 = "}";
  protected final String TEXT_366 = ",";
  protected final String TEXT_367 = NL + "\t * The list contents are of type {@link ";
  protected final String TEXT_368 = "}";
  protected final String TEXT_369 = ".";
  protected final String TEXT_370 = NL + "\t * The default value is <code>";
  protected final String TEXT_371 = "</code>.";
  protected final String TEXT_372 = NL + "\t * The literals are from the enumeration {@link ";
  protected final String TEXT_373 = "}.";
  protected final String TEXT_374 = NL + "\t * It is bidirectional and its opposite is '{@link ";
  protected final String TEXT_375 = "#";
  protected final String TEXT_376 = " <em>";
  protected final String TEXT_377 = "</em>}'.";
  protected final String TEXT_378 = NL + "\t * <!-- begin-user-doc -->";
  protected final String TEXT_379 = NL + "\t * <p>" + NL + "\t * If the meaning of the '<em>";
  protected final String TEXT_380 = "</em>' ";
  protected final String TEXT_381 = " isn't clear," + NL + "\t * there really should be more of a description here..." + NL + "\t * </p>";
  protected final String TEXT_382 = NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_383 = NL + "\t * <!-- begin-model-doc -->" + NL + "\t * ";
  protected final String TEXT_384 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_385 = NL + "\t * @return the value of the '<em>";
  protected final String TEXT_386 = "</em>' ";
  protected final String TEXT_387 = ".";
  protected final String TEXT_388 = NL + "\t * @see ";
  protected final String TEXT_389 = NL + "\t * @see #isSet";
  protected final String TEXT_390 = "()";
  protected final String TEXT_391 = NL + "\t * @see #unset";
  protected final String TEXT_392 = "()";
  protected final String TEXT_393 = NL + "\t * @see #set";
  protected final String TEXT_394 = "(";
  protected final String TEXT_395 = ")";
  protected final String TEXT_396 = NL + "\t * @see ";
  protected final String TEXT_397 = "#get";
  protected final String TEXT_398 = "()";
  protected final String TEXT_399 = NL + "\t * @see ";
  protected final String TEXT_400 = "#";
  protected final String TEXT_401 = NL + "\t * @model ";
  protected final String TEXT_402 = NL + "\t *        ";
  protected final String TEXT_403 = NL + "\t * @model";
  protected final String TEXT_404 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_405 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_406 = NL + "\t";
  protected final String TEXT_407 = " ";
  protected final String TEXT_408 = "();" + NL;
  protected final String TEXT_409 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_410 = NL + "\tpublic ";
  protected final String TEXT_411 = " ";
  protected final String TEXT_412 = "_";
  protected final String TEXT_413 = "()" + NL + "\t{";
  protected final String TEXT_414 = NL + "\t\treturn ";
  protected final String TEXT_415 = "(";
  protected final String TEXT_416 = "(";
  protected final String TEXT_417 = ")eDynamicGet(";
  protected final String TEXT_418 = " - ESTATIC_FEATURE_COUNT";
  protected final String TEXT_419 = ", ";
  protected final String TEXT_420 = ", true, ";
  protected final String TEXT_421 = ")";
  protected final String TEXT_422 = ").";
  protected final String TEXT_423 = "()";
  protected final String TEXT_424 = ";";
  protected final String TEXT_425 = NL + "\t\t";
  protected final String TEXT_426 = NL + "\t\treturn ";
  protected final String TEXT_427 = "(";
  protected final String TEXT_428 = "(";
  protected final String TEXT_429 = ")eGet(";
  protected final String TEXT_430 = ", true)";
  protected final String TEXT_431 = ").";
  protected final String TEXT_432 = "()";
  protected final String TEXT_433 = ";";
  protected final String TEXT_434 = NL + "\t\treturn ";
  protected final String TEXT_435 = "(";
  protected final String TEXT_436 = "(";
  protected final String TEXT_437 = ")";
  protected final String TEXT_438 = "__ESETTING_DELEGATE.dynamicGet(this, null, 0, true, false)";
  protected final String TEXT_439 = ").";
  protected final String TEXT_440 = "()";
  protected final String TEXT_441 = ";";
  protected final String TEXT_442 = NL + "\t\t";
  protected final String TEXT_443 = " ";
  protected final String TEXT_444 = " = (";
  protected final String TEXT_445 = ")eVirtualGet(";
  protected final String TEXT_446 = ");";
  protected final String TEXT_447 = NL + "\t\tif (";
  protected final String TEXT_448 = " == null)" + NL + "\t\t{";
  protected final String TEXT_449 = NL + "\t\t\teVirtualSet(";
  protected final String TEXT_450 = ", ";
  protected final String TEXT_451 = " = new ";
  protected final String TEXT_452 = ");";
  protected final String TEXT_453 = NL + "       \t\t";
  protected final String TEXT_454 = " = new ";
  protected final String TEXT_455 = ";" + NL + "       \t ";
  protected final String TEXT_456 = NL + "\t\t\t";
  protected final String TEXT_457 = " = new ";
  protected final String TEXT_458 = ";" + NL + "\t\t ";
  protected final String TEXT_459 = NL + "\t\t";
  protected final String TEXT_460 = NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_461 = ";";
  protected final String TEXT_462 = NL + "\t\tif (eContainerFeatureID() != ";
  protected final String TEXT_463 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_464 = ")";
  protected final String TEXT_465 = "eContainer";
  protected final String TEXT_466 = "eInternalContainer";
  protected final String TEXT_467 = "();";
  protected final String TEXT_468 = NL + "\t\t";
  protected final String TEXT_469 = " ";
  protected final String TEXT_470 = " = (";
  protected final String TEXT_471 = ")eVirtualGet(";
  protected final String TEXT_472 = ", ";
  protected final String TEXT_473 = ");";
  protected final String TEXT_474 = NL + "\t\tif (";
  protected final String TEXT_475 = " != null && ";
  protected final String TEXT_476 = ".eIsProxy())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_477 = " old";
  protected final String TEXT_478 = " = (";
  protected final String TEXT_479 = ")";
  protected final String TEXT_480 = ";" + NL + "\t\t\t";
  protected final String TEXT_481 = " = ";
  protected final String TEXT_482 = "eResolveProxy(old";
  protected final String TEXT_483 = ");" + NL + "\t\t\tif (";
  protected final String TEXT_484 = " != old";
  protected final String TEXT_485 = ")" + NL + "\t\t\t{";
  protected final String TEXT_486 = NL + "\t\t\t\t";
  protected final String TEXT_487 = " new";
  protected final String TEXT_488 = " = (";
  protected final String TEXT_489 = ")";
  protected final String TEXT_490 = ";";
  protected final String TEXT_491 = NL + "\t\t\t\t";
  protected final String TEXT_492 = " msgs = old";
  protected final String TEXT_493 = ".eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_494 = ", null, null);";
  protected final String TEXT_495 = NL + "\t\t\t\t";
  protected final String TEXT_496 = " msgs =  old";
  protected final String TEXT_497 = ".eInverseRemove(this, ";
  protected final String TEXT_498 = ", ";
  protected final String TEXT_499 = ".class, null);";
  protected final String TEXT_500 = NL + "\t\t\t\tif (new";
  protected final String TEXT_501 = ".eInternalContainer() == null)" + NL + "\t\t\t\t{";
  protected final String TEXT_502 = NL + "\t\t\t\t\tmsgs = new";
  protected final String TEXT_503 = ".eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_504 = ", null, msgs);";
  protected final String TEXT_505 = NL + "\t\t\t\t\tmsgs =  new";
  protected final String TEXT_506 = ".eInverseAdd(this, ";
  protected final String TEXT_507 = ", ";
  protected final String TEXT_508 = ".class, msgs);";
  protected final String TEXT_509 = NL + "\t\t\t\t}" + NL + "\t\t\t\tif (msgs != null) msgs.dispatch();";
  protected final String TEXT_510 = NL + "\t\t\t\teVirtualSet(";
  protected final String TEXT_511 = ", ";
  protected final String TEXT_512 = ");";
  protected final String TEXT_513 = NL + "\t\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\t\teNotify(new ";
  protected final String TEXT_514 = "(this, ";
  protected final String TEXT_515 = ".RESOLVE, ";
  protected final String TEXT_516 = ", old";
  protected final String TEXT_517 = ", ";
  protected final String TEXT_518 = "));";
  protected final String TEXT_519 = NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_520 = NL + "\t\treturn (";
  protected final String TEXT_521 = ")eVirtualGet(";
  protected final String TEXT_522 = ", ";
  protected final String TEXT_523 = ");";
  protected final String TEXT_524 = NL + "\t\treturn (";
  protected final String TEXT_525 = " & ";
  protected final String TEXT_526 = "_EFLAG) != 0;";
  protected final String TEXT_527 = NL + "\t\treturn ";
  protected final String TEXT_528 = "_EFLAG_VALUES[(";
  protected final String TEXT_529 = " & ";
  protected final String TEXT_530 = "_EFLAG) >>> ";
  protected final String TEXT_531 = "_EFLAG_OFFSET];";
  protected final String TEXT_532 = NL + "\t\treturn ";
  protected final String TEXT_533 = ";";
  protected final String TEXT_534 = NL + "\t\t";
  protected final String TEXT_535 = " ";
  protected final String TEXT_536 = " = basicGet";
  protected final String TEXT_537 = "();" + NL + "\t\treturn ";
  protected final String TEXT_538 = " != null && ";
  protected final String TEXT_539 = ".eIsProxy() ? ";
  protected final String TEXT_540 = "eResolveProxy((";
  protected final String TEXT_541 = ")";
  protected final String TEXT_542 = ") : ";
  protected final String TEXT_543 = ";";
  protected final String TEXT_544 = NL + "\t\treturn new ";
  protected final String TEXT_545 = "((";
  protected final String TEXT_546 = ".Internal)((";
  protected final String TEXT_547 = ".Internal.Wrapper)get";
  protected final String TEXT_548 = "()).featureMap().";
  protected final String TEXT_549 = "list(";
  protected final String TEXT_550 = "));";
  protected final String TEXT_551 = NL + "\t\treturn (";
  protected final String TEXT_552 = ")get";
  protected final String TEXT_553 = "().";
  protected final String TEXT_554 = "list(";
  protected final String TEXT_555 = ");";
  protected final String TEXT_556 = NL + "\t\treturn ((";
  protected final String TEXT_557 = ".Internal.Wrapper)get";
  protected final String TEXT_558 = "()).featureMap().list(";
  protected final String TEXT_559 = ");";
  protected final String TEXT_560 = NL + "\t\treturn get";
  protected final String TEXT_561 = "().list(";
  protected final String TEXT_562 = ");";
  protected final String TEXT_563 = NL + "\t\treturn ";
  protected final String TEXT_564 = "(";
  protected final String TEXT_565 = "(";
  protected final String TEXT_566 = ")";
  protected final String TEXT_567 = "((";
  protected final String TEXT_568 = ".Internal.Wrapper)get";
  protected final String TEXT_569 = "()).featureMap().get(";
  protected final String TEXT_570 = ", true)";
  protected final String TEXT_571 = ").";
  protected final String TEXT_572 = "()";
  protected final String TEXT_573 = ";";
  protected final String TEXT_574 = NL + "\t\treturn ";
  protected final String TEXT_575 = "(";
  protected final String TEXT_576 = "(";
  protected final String TEXT_577 = ")";
  protected final String TEXT_578 = "get";
  protected final String TEXT_579 = "().get(";
  protected final String TEXT_580 = ", true)";
  protected final String TEXT_581 = ").";
  protected final String TEXT_582 = "()";
  protected final String TEXT_583 = ";";
  protected final String TEXT_584 = NL + "\t\t";
  protected final String TEXT_585 = NL + "\t\t";
  protected final String TEXT_586 = NL + "\t\t// *********************************" + NL + "\t\t//  VirSat Specific Code Generation" + NL + "\t\t// *********************************" + NL + "     \treturn ";
  protected final String TEXT_587 = ".getFullQualifiedId(this);";
  protected final String TEXT_588 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_589 = "' ";
  protected final String TEXT_590 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT";
  protected final String TEXT_591 = NL + "\t\t// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting" + NL + "\t\t// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.";
  protected final String TEXT_592 = "EcoreEMap";
  protected final String TEXT_593 = "BasicFeatureMap";
  protected final String TEXT_594 = "EcoreEList";
  protected final String TEXT_595 = " should be used.";
  protected final String TEXT_596 = NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_597 = NL + "\t}" + NL;
  protected final String TEXT_598 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_599 = NL + "\tpublic ";
  protected final String TEXT_600 = " basicGet";
  protected final String TEXT_601 = "()" + NL + "\t{";
  protected final String TEXT_602 = NL + "\t\treturn (";
  protected final String TEXT_603 = ")eDynamicGet(";
  protected final String TEXT_604 = " - ESTATIC_FEATURE_COUNT";
  protected final String TEXT_605 = ", ";
  protected final String TEXT_606 = ", false, ";
  protected final String TEXT_607 = ");";
  protected final String TEXT_608 = NL + "\t\treturn ";
  protected final String TEXT_609 = "(";
  protected final String TEXT_610 = "(";
  protected final String TEXT_611 = ")";
  protected final String TEXT_612 = "__ESETTING_DELEGATE.dynamicGet(this, null, 0, false, false)";
  protected final String TEXT_613 = ").";
  protected final String TEXT_614 = "()";
  protected final String TEXT_615 = ";";
  protected final String TEXT_616 = NL + "\t\tif (eContainerFeatureID() != ";
  protected final String TEXT_617 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_618 = ")eInternalContainer();";
  protected final String TEXT_619 = NL + "\t\treturn (";
  protected final String TEXT_620 = ")eVirtualGet(";
  protected final String TEXT_621 = ");";
  protected final String TEXT_622 = NL + "\t\treturn ";
  protected final String TEXT_623 = ";";
  protected final String TEXT_624 = NL + "\t\treturn (";
  protected final String TEXT_625 = ")((";
  protected final String TEXT_626 = ".Internal.Wrapper)get";
  protected final String TEXT_627 = "()).featureMap().get(";
  protected final String TEXT_628 = ", false);";
  protected final String TEXT_629 = NL + "\t\treturn (";
  protected final String TEXT_630 = ")get";
  protected final String TEXT_631 = "().get(";
  protected final String TEXT_632 = ", false);";
  protected final String TEXT_633 = NL + "\t\t";
  protected final String TEXT_634 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_635 = "' ";
  protected final String TEXT_636 = NL + "\t\t// -> do not perform proxy resolution" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_637 = NL + "\t}" + NL;
  protected final String TEXT_638 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_639 = NL + "\tpublic ";
  protected final String TEXT_640 = " basicSet";
  protected final String TEXT_641 = "(";
  protected final String TEXT_642 = " new";
  protected final String TEXT_643 = ", ";
  protected final String TEXT_644 = " msgs)" + NL + "\t{";
  protected final String TEXT_645 = NL + "\t\tmsgs = eBasicSetContainer((";
  protected final String TEXT_646 = ")new";
  protected final String TEXT_647 = ", ";
  protected final String TEXT_648 = ", msgs);";
  protected final String TEXT_649 = NL + "\t\treturn msgs;";
  protected final String TEXT_650 = NL + "\t\tmsgs = eDynamicInverseAdd((";
  protected final String TEXT_651 = ")new";
  protected final String TEXT_652 = ", ";
  protected final String TEXT_653 = ", msgs);";
  protected final String TEXT_654 = NL + "\t\treturn msgs;";
  protected final String TEXT_655 = NL + "\t\tObject old";
  protected final String TEXT_656 = " = eVirtualSet(";
  protected final String TEXT_657 = ", new";
  protected final String TEXT_658 = ");";
  protected final String TEXT_659 = NL + "\t\t";
  protected final String TEXT_660 = " old";
  protected final String TEXT_661 = " = ";
  protected final String TEXT_662 = ";" + NL + "\t\t";
  protected final String TEXT_663 = " = new";
  protected final String TEXT_664 = ";";
  protected final String TEXT_665 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_666 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_667 = NL + "\t\tboolean old";
  protected final String TEXT_668 = "ESet = (";
  protected final String TEXT_669 = " & ";
  protected final String TEXT_670 = "_ESETFLAG) != 0;";
  protected final String TEXT_671 = NL + "\t\t";
  protected final String TEXT_672 = " |= ";
  protected final String TEXT_673 = "_ESETFLAG;";
  protected final String TEXT_674 = NL + "\t\tboolean old";
  protected final String TEXT_675 = "ESet = ";
  protected final String TEXT_676 = "ESet;";
  protected final String TEXT_677 = NL + "\t\t";
  protected final String TEXT_678 = "ESet = true;";
  protected final String TEXT_679 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{";
  protected final String TEXT_680 = NL + "\t\t\t";
  protected final String TEXT_681 = " notification = new ";
  protected final String TEXT_682 = "(this, ";
  protected final String TEXT_683 = ".SET, ";
  protected final String TEXT_684 = ", ";
  protected final String TEXT_685 = "isSetChange ? null : old";
  protected final String TEXT_686 = "old";
  protected final String TEXT_687 = ", new";
  protected final String TEXT_688 = ", ";
  protected final String TEXT_689 = "isSetChange";
  protected final String TEXT_690 = "!old";
  protected final String TEXT_691 = "ESet";
  protected final String TEXT_692 = ");";
  protected final String TEXT_693 = NL + "\t\t\t";
  protected final String TEXT_694 = " notification = new ";
  protected final String TEXT_695 = "(this, ";
  protected final String TEXT_696 = ".SET, ";
  protected final String TEXT_697 = ", ";
  protected final String TEXT_698 = "old";
  protected final String TEXT_699 = " == EVIRTUAL_NO_VALUE ? null : old";
  protected final String TEXT_700 = "old";
  protected final String TEXT_701 = ", new";
  protected final String TEXT_702 = ");";
  protected final String TEXT_703 = NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}";
  protected final String TEXT_704 = NL + "\t\treturn msgs;";
  protected final String TEXT_705 = NL + "\t\treturn ((";
  protected final String TEXT_706 = ".Internal)((";
  protected final String TEXT_707 = ".Internal.Wrapper)get";
  protected final String TEXT_708 = "()).featureMap()).basicAdd(";
  protected final String TEXT_709 = ", new";
  protected final String TEXT_710 = ", msgs);";
  protected final String TEXT_711 = NL + "\t\treturn ((";
  protected final String TEXT_712 = ".Internal)get";
  protected final String TEXT_713 = "()).basicAdd(";
  protected final String TEXT_714 = ", new";
  protected final String TEXT_715 = ", msgs);";
  protected final String TEXT_716 = NL + "\t\t// TODO: implement this method to set the contained '";
  protected final String TEXT_717 = "' ";
  protected final String TEXT_718 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_719 = NL + "\t}" + NL;
  protected final String TEXT_720 = NL + "\t/**" + NL + "\t * Sets the value of the '{@link ";
  protected final String TEXT_721 = "#";
  protected final String TEXT_722 = " <em>";
  protected final String TEXT_723 = "</em>}' ";
  protected final String TEXT_724 = ".";
  protected final String TEXT_725 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param value the new value of the '<em>";
  protected final String TEXT_726 = "</em>' ";
  protected final String TEXT_727 = ".";
  protected final String TEXT_728 = NL + "\t * @see ";
  protected final String TEXT_729 = NL + "\t * @see #isSet";
  protected final String TEXT_730 = "()";
  protected final String TEXT_731 = NL + "\t * @see #unset";
  protected final String TEXT_732 = "()";
  protected final String TEXT_733 = NL + "\t * @see #";
  protected final String TEXT_734 = "()" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_735 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_736 = NL + "\tvoid set";
  protected final String TEXT_737 = "(";
  protected final String TEXT_738 = " value);" + NL;
  protected final String TEXT_739 = NL + "\tpublic void set";
  protected final String TEXT_740 = "_";
  protected final String TEXT_741 = "(";
  protected final String TEXT_742 = " ";
  protected final String TEXT_743 = ")" + NL + "\t{";
  protected final String TEXT_744 = NL + "\t\tif (!";
  protected final String TEXT_745 = ".isValid(this, ";
  protected final String TEXT_746 = ")) {" + NL + "\t\t\treturn;" + NL + "\t\t}";
  protected final String TEXT_747 = NL + "\t\teDynamicSet(";
  protected final String TEXT_748 = " - ESTATIC_FEATURE_COUNT";
  protected final String TEXT_749 = ", ";
  protected final String TEXT_750 = ", ";
  protected final String TEXT_751 = "new ";
  protected final String TEXT_752 = "(";
  protected final String TEXT_753 = "new";
  protected final String TEXT_754 = ")";
  protected final String TEXT_755 = ");";
  protected final String TEXT_756 = NL + "\t\teSet(";
  protected final String TEXT_757 = ", ";
  protected final String TEXT_758 = "new ";
  protected final String TEXT_759 = "(";
  protected final String TEXT_760 = "new";
  protected final String TEXT_761 = ")";
  protected final String TEXT_762 = ");";
  protected final String TEXT_763 = NL + "\t\t";
  protected final String TEXT_764 = "__ESETTING_DELEGATE.dynamicSet(this, null, 0, ";
  protected final String TEXT_765 = "new ";
  protected final String TEXT_766 = "(";
  protected final String TEXT_767 = "new";
  protected final String TEXT_768 = ")";
  protected final String TEXT_769 = ");";
  protected final String TEXT_770 = NL + "\t\tif (new";
  protected final String TEXT_771 = " != eInternalContainer() || (eContainerFeatureID() != ";
  protected final String TEXT_772 = " && new";
  protected final String TEXT_773 = " != null))" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_774 = ".isAncestor(this, ";
  protected final String TEXT_775 = "new";
  protected final String TEXT_776 = "))" + NL + "\t\t\t\tthrow new ";
  protected final String TEXT_777 = "(\"Recursive containment not allowed for \" + toString());";
  protected final String TEXT_778 = NL + "\t\t\t";
  protected final String TEXT_779 = " msgs = null;" + NL + "\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_780 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_781 = ")new";
  protected final String TEXT_782 = ").eInverseAdd(this, ";
  protected final String TEXT_783 = ", ";
  protected final String TEXT_784 = ".class, msgs);" + NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_785 = "(";
  protected final String TEXT_786 = "new";
  protected final String TEXT_787 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_788 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_789 = "(this, ";
  protected final String TEXT_790 = ".SET, ";
  protected final String TEXT_791 = ", new";
  protected final String TEXT_792 = ", new";
  protected final String TEXT_793 = "));";
  protected final String TEXT_794 = NL + "\t\t";
  protected final String TEXT_795 = " ";
  protected final String TEXT_796 = " = (";
  protected final String TEXT_797 = ")eVirtualGet(";
  protected final String TEXT_798 = ");";
  protected final String TEXT_799 = NL + "\t\tif (new";
  protected final String TEXT_800 = " != ";
  protected final String TEXT_801 = ")" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_802 = " msgs = null;" + NL + "\t\t\tif (";
  protected final String TEXT_803 = " != null)";
  protected final String TEXT_804 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_805 = ")";
  protected final String TEXT_806 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_807 = ", null, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_808 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_809 = ")new";
  protected final String TEXT_810 = ").eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_811 = ", null, msgs);";
  protected final String TEXT_812 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_813 = ")";
  protected final String TEXT_814 = ").eInverseRemove(this, ";
  protected final String TEXT_815 = ", ";
  protected final String TEXT_816 = ".class, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_817 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_818 = ")new";
  protected final String TEXT_819 = ").eInverseAdd(this, ";
  protected final String TEXT_820 = ", ";
  protected final String TEXT_821 = ".class, msgs);";
  protected final String TEXT_822 = NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_823 = "(";
  protected final String TEXT_824 = "new";
  protected final String TEXT_825 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_826 = NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_827 = NL + "\t\t\tboolean old";
  protected final String TEXT_828 = "ESet = eVirtualIsSet(";
  protected final String TEXT_829 = ");";
  protected final String TEXT_830 = NL + "\t\t\tboolean old";
  protected final String TEXT_831 = "ESet = (";
  protected final String TEXT_832 = " & ";
  protected final String TEXT_833 = "_ESETFLAG) != 0;";
  protected final String TEXT_834 = NL + "\t\t\t";
  protected final String TEXT_835 = " |= ";
  protected final String TEXT_836 = "_ESETFLAG;";
  protected final String TEXT_837 = NL + "\t\t\tboolean old";
  protected final String TEXT_838 = "ESet = ";
  protected final String TEXT_839 = "ESet;";
  protected final String TEXT_840 = NL + "\t\t\t";
  protected final String TEXT_841 = "ESet = true;";
  protected final String TEXT_842 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_843 = "(this, ";
  protected final String TEXT_844 = ".SET, ";
  protected final String TEXT_845 = ", new";
  protected final String TEXT_846 = ", new";
  protected final String TEXT_847 = ", !old";
  protected final String TEXT_848 = "ESet));";
  protected final String TEXT_849 = NL + "\t\t}";
  protected final String TEXT_850 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_851 = "(this, ";
  protected final String TEXT_852 = ".SET, ";
  protected final String TEXT_853 = ", new";
  protected final String TEXT_854 = ", new";
  protected final String TEXT_855 = "));";
  protected final String TEXT_856 = NL + "\t\t";
  protected final String TEXT_857 = " old";
  protected final String TEXT_858 = " = (";
  protected final String TEXT_859 = " & ";
  protected final String TEXT_860 = "_EFLAG) != 0;";
  protected final String TEXT_861 = NL + "\t\t";
  protected final String TEXT_862 = " old";
  protected final String TEXT_863 = " = ";
  protected final String TEXT_864 = "_EFLAG_VALUES[(";
  protected final String TEXT_865 = " & ";
  protected final String TEXT_866 = "_EFLAG) >>> ";
  protected final String TEXT_867 = "_EFLAG_OFFSET];";
  protected final String TEXT_868 = NL + "\t\tif (new";
  protected final String TEXT_869 = ") ";
  protected final String TEXT_870 = " |= ";
  protected final String TEXT_871 = "_EFLAG; else ";
  protected final String TEXT_872 = " &= ~";
  protected final String TEXT_873 = "_EFLAG;";
  protected final String TEXT_874 = NL + "\t\tif (new";
  protected final String TEXT_875 = " == null) new";
  protected final String TEXT_876 = " = ";
  protected final String TEXT_877 = "_EDEFAULT;" + NL + "\t\t";
  protected final String TEXT_878 = " = ";
  protected final String TEXT_879 = " & ~";
  protected final String TEXT_880 = "_EFLAG | ";
  protected final String TEXT_881 = "new";
  protected final String TEXT_882 = ".ordinal()";
  protected final String TEXT_883 = ".VALUES.indexOf(new";
  protected final String TEXT_884 = ")";
  protected final String TEXT_885 = " << ";
  protected final String TEXT_886 = "_EFLAG_OFFSET;";
  protected final String TEXT_887 = NL + "\t\t";
  protected final String TEXT_888 = " old";
  protected final String TEXT_889 = " = ";
  protected final String TEXT_890 = ";";
  protected final String TEXT_891 = NL + "\t\t";
  protected final String TEXT_892 = " ";
  protected final String TEXT_893 = " = new";
  protected final String TEXT_894 = " == null ? ";
  protected final String TEXT_895 = " : new";
  protected final String TEXT_896 = ";";
  protected final String TEXT_897 = NL + "\t\t";
  protected final String TEXT_898 = " = new";
  protected final String TEXT_899 = " == null ? ";
  protected final String TEXT_900 = " : new";
  protected final String TEXT_901 = ";";
  protected final String TEXT_902 = NL + "\t\t";
  protected final String TEXT_903 = " ";
  protected final String TEXT_904 = " = ";
  protected final String TEXT_905 = "new";
  protected final String TEXT_906 = ";";
  protected final String TEXT_907 = NL + "\t\t";
  protected final String TEXT_908 = " = ";
  protected final String TEXT_909 = "new";
  protected final String TEXT_910 = ";";
  protected final String TEXT_911 = NL + "\t\tObject old";
  protected final String TEXT_912 = " = eVirtualSet(";
  protected final String TEXT_913 = ", ";
  protected final String TEXT_914 = ");";
  protected final String TEXT_915 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_916 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_917 = NL + "\t\tboolean old";
  protected final String TEXT_918 = "ESet = (";
  protected final String TEXT_919 = " & ";
  protected final String TEXT_920 = "_ESETFLAG) != 0;";
  protected final String TEXT_921 = NL + "\t\t";
  protected final String TEXT_922 = " |= ";
  protected final String TEXT_923 = "_ESETFLAG;";
  protected final String TEXT_924 = NL + "\t\tboolean old";
  protected final String TEXT_925 = "ESet = ";
  protected final String TEXT_926 = "ESet;";
  protected final String TEXT_927 = NL + "\t\t";
  protected final String TEXT_928 = "ESet = true;";
  protected final String TEXT_929 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_930 = "(this, ";
  protected final String TEXT_931 = ".SET, ";
  protected final String TEXT_932 = ", ";
  protected final String TEXT_933 = "isSetChange ? ";
  protected final String TEXT_934 = " : old";
  protected final String TEXT_935 = "old";
  protected final String TEXT_936 = ", ";
  protected final String TEXT_937 = "new";
  protected final String TEXT_938 = ", ";
  protected final String TEXT_939 = "isSetChange";
  protected final String TEXT_940 = "!old";
  protected final String TEXT_941 = "ESet";
  protected final String TEXT_942 = "));";
  protected final String TEXT_943 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_944 = "(this, ";
  protected final String TEXT_945 = ".SET, ";
  protected final String TEXT_946 = ", ";
  protected final String TEXT_947 = "old";
  protected final String TEXT_948 = " == EVIRTUAL_NO_VALUE ? ";
  protected final String TEXT_949 = " : old";
  protected final String TEXT_950 = "old";
  protected final String TEXT_951 = ", ";
  protected final String TEXT_952 = "new";
  protected final String TEXT_953 = "));";
  protected final String TEXT_954 = NL + "\t\t((";
  protected final String TEXT_955 = ".Internal)((";
  protected final String TEXT_956 = ".Internal.Wrapper)get";
  protected final String TEXT_957 = "()).featureMap()).set(";
  protected final String TEXT_958 = ", ";
  protected final String TEXT_959 = "new ";
  protected final String TEXT_960 = "(";
  protected final String TEXT_961 = "new";
  protected final String TEXT_962 = ")";
  protected final String TEXT_963 = ");";
  protected final String TEXT_964 = NL + "\t\t((";
  protected final String TEXT_965 = ".Internal)get";
  protected final String TEXT_966 = "()).set(";
  protected final String TEXT_967 = ", ";
  protected final String TEXT_968 = "new ";
  protected final String TEXT_969 = "(";
  protected final String TEXT_970 = "new";
  protected final String TEXT_971 = ")";
  protected final String TEXT_972 = ");";
  protected final String TEXT_973 = NL + "\t\t";
  protected final String TEXT_974 = NL + "\t\t// TODO: implement this method to set the '";
  protected final String TEXT_975 = "' ";
  protected final String TEXT_976 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_977 = NL + "\t}" + NL;
  protected final String TEXT_978 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_979 = NL + "\tpublic ";
  protected final String TEXT_980 = " basicUnset";
  protected final String TEXT_981 = "(";
  protected final String TEXT_982 = " msgs)" + NL + "\t{";
  protected final String TEXT_983 = NL + "\t\treturn eDynamicInverseRemove((";
  protected final String TEXT_984 = ")";
  protected final String TEXT_985 = "basicGet";
  protected final String TEXT_986 = "(), ";
  protected final String TEXT_987 = ", msgs);";
  protected final String TEXT_988 = "Object old";
  protected final String TEXT_989 = " = ";
  protected final String TEXT_990 = "eVirtualUnset(";
  protected final String TEXT_991 = ");";
  protected final String TEXT_992 = NL + "\t\t";
  protected final String TEXT_993 = " old";
  protected final String TEXT_994 = " = ";
  protected final String TEXT_995 = ";";
  protected final String TEXT_996 = NL + "\t\t";
  protected final String TEXT_997 = " = null;";
  protected final String TEXT_998 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_999 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_1000 = NL + "\t\tboolean old";
  protected final String TEXT_1001 = "ESet = (";
  protected final String TEXT_1002 = " & ";
  protected final String TEXT_1003 = "_ESETFLAG) != 0;";
  protected final String TEXT_1004 = NL + "\t\t";
  protected final String TEXT_1005 = " &= ~";
  protected final String TEXT_1006 = "_ESETFLAG;";
  protected final String TEXT_1007 = NL + "\t\tboolean old";
  protected final String TEXT_1008 = "ESet = ";
  protected final String TEXT_1009 = "ESet;";
  protected final String TEXT_1010 = NL + "\t\t";
  protected final String TEXT_1011 = "ESet = false;";
  protected final String TEXT_1012 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_1013 = " notification = new ";
  protected final String TEXT_1014 = "(this, ";
  protected final String TEXT_1015 = ".UNSET, ";
  protected final String TEXT_1016 = ", ";
  protected final String TEXT_1017 = "isSetChange ? old";
  protected final String TEXT_1018 = " : null";
  protected final String TEXT_1019 = "old";
  protected final String TEXT_1020 = ", null, ";
  protected final String TEXT_1021 = "isSetChange";
  protected final String TEXT_1022 = "old";
  protected final String TEXT_1023 = "ESet";
  protected final String TEXT_1024 = ");" + NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_1025 = NL + "\t\t// TODO: implement this method to unset the contained '";
  protected final String TEXT_1026 = "' ";
  protected final String TEXT_1027 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1028 = NL + "\t}" + NL;
  protected final String TEXT_1029 = NL + "\t/**" + NL + "\t * Unsets the value of the '{@link ";
  protected final String TEXT_1030 = "#";
  protected final String TEXT_1031 = " <em>";
  protected final String TEXT_1032 = "</em>}' ";
  protected final String TEXT_1033 = ".";
  protected final String TEXT_1034 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_1035 = NL + "\t * @see #isSet";
  protected final String TEXT_1036 = "()";
  protected final String TEXT_1037 = NL + "\t * @see #";
  protected final String TEXT_1038 = "()";
  protected final String TEXT_1039 = NL + "\t * @see #set";
  protected final String TEXT_1040 = "(";
  protected final String TEXT_1041 = ")";
  protected final String TEXT_1042 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1043 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1044 = NL + "\tvoid unset";
  protected final String TEXT_1045 = "();" + NL;
  protected final String TEXT_1046 = NL + "\tpublic void unset";
  protected final String TEXT_1047 = "_";
  protected final String TEXT_1048 = "()" + NL + "\t{";
  protected final String TEXT_1049 = NL + "\t\teDynamicUnset(";
  protected final String TEXT_1050 = " - ESTATIC_FEATURE_COUNT";
  protected final String TEXT_1051 = ", ";
  protected final String TEXT_1052 = ");";
  protected final String TEXT_1053 = NL + "\t\teUnset(";
  protected final String TEXT_1054 = ");";
  protected final String TEXT_1055 = NL + "\t\t";
  protected final String TEXT_1056 = "__ESETTING_DELEGATE.dynamicUnset(this, null, 0);";
  protected final String TEXT_1057 = NL + "\t\t";
  protected final String TEXT_1058 = " ";
  protected final String TEXT_1059 = " = (";
  protected final String TEXT_1060 = ")eVirtualGet(";
  protected final String TEXT_1061 = ");";
  protected final String TEXT_1062 = NL + "\t\tif (";
  protected final String TEXT_1063 = " != null) ((";
  protected final String TEXT_1064 = ".Unsettable";
  protected final String TEXT_1065 = ")";
  protected final String TEXT_1066 = ").unset();";
  protected final String TEXT_1067 = NL + "\t\t";
  protected final String TEXT_1068 = " ";
  protected final String TEXT_1069 = " = (";
  protected final String TEXT_1070 = ")eVirtualGet(";
  protected final String TEXT_1071 = ");";
  protected final String TEXT_1072 = NL + "\t\tif (";
  protected final String TEXT_1073 = " != null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_1074 = " msgs = null;";
  protected final String TEXT_1075 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_1076 = ")";
  protected final String TEXT_1077 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_1078 = ", null, msgs);";
  protected final String TEXT_1079 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_1080 = ")";
  protected final String TEXT_1081 = ").eInverseRemove(this, ";
  protected final String TEXT_1082 = ", ";
  protected final String TEXT_1083 = ".class, msgs);";
  protected final String TEXT_1084 = NL + "\t\t\tmsgs = basicUnset";
  protected final String TEXT_1085 = "(msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_1086 = NL + "\t\t\tboolean old";
  protected final String TEXT_1087 = "ESet = eVirtualIsSet(";
  protected final String TEXT_1088 = ");";
  protected final String TEXT_1089 = NL + "\t\t\tboolean old";
  protected final String TEXT_1090 = "ESet = (";
  protected final String TEXT_1091 = " & ";
  protected final String TEXT_1092 = "_ESETFLAG) != 0;";
  protected final String TEXT_1093 = NL + "\t\t\t";
  protected final String TEXT_1094 = " &= ~";
  protected final String TEXT_1095 = "_ESETFLAG;";
  protected final String TEXT_1096 = NL + "\t\t\tboolean old";
  protected final String TEXT_1097 = "ESet = ";
  protected final String TEXT_1098 = "ESet;";
  protected final String TEXT_1099 = NL + "\t\t\t";
  protected final String TEXT_1100 = "ESet = false;";
  protected final String TEXT_1101 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_1102 = "(this, ";
  protected final String TEXT_1103 = ".UNSET, ";
  protected final String TEXT_1104 = ", null, null, old";
  protected final String TEXT_1105 = "ESet));";
  protected final String TEXT_1106 = NL + "\t\t}";
  protected final String TEXT_1107 = NL + "\t\t";
  protected final String TEXT_1108 = " old";
  protected final String TEXT_1109 = " = (";
  protected final String TEXT_1110 = " & ";
  protected final String TEXT_1111 = "_EFLAG) != 0;";
  protected final String TEXT_1112 = NL + "\t\t";
  protected final String TEXT_1113 = " old";
  protected final String TEXT_1114 = " = ";
  protected final String TEXT_1115 = "_EFLAG_VALUES[(";
  protected final String TEXT_1116 = " & ";
  protected final String TEXT_1117 = "_EFLAG) >>> ";
  protected final String TEXT_1118 = "_EFLAG_OFFSET];";
  protected final String TEXT_1119 = NL + "\t\tObject old";
  protected final String TEXT_1120 = " = eVirtualUnset(";
  protected final String TEXT_1121 = ");";
  protected final String TEXT_1122 = NL + "\t\t";
  protected final String TEXT_1123 = " old";
  protected final String TEXT_1124 = " = ";
  protected final String TEXT_1125 = ";";
  protected final String TEXT_1126 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_1127 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_1128 = NL + "\t\tboolean old";
  protected final String TEXT_1129 = "ESet = (";
  protected final String TEXT_1130 = " & ";
  protected final String TEXT_1131 = "_ESETFLAG) != 0;";
  protected final String TEXT_1132 = NL + "\t\tboolean old";
  protected final String TEXT_1133 = "ESet = ";
  protected final String TEXT_1134 = "ESet;";
  protected final String TEXT_1135 = NL + "\t\t";
  protected final String TEXT_1136 = " = null;";
  protected final String TEXT_1137 = NL + "\t\t";
  protected final String TEXT_1138 = " &= ~";
  protected final String TEXT_1139 = "_ESETFLAG;";
  protected final String TEXT_1140 = NL + "\t\t";
  protected final String TEXT_1141 = "ESet = false;";
  protected final String TEXT_1142 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_1143 = "(this, ";
  protected final String TEXT_1144 = ".UNSET, ";
  protected final String TEXT_1145 = ", ";
  protected final String TEXT_1146 = "isSetChange ? old";
  protected final String TEXT_1147 = " : null";
  protected final String TEXT_1148 = "old";
  protected final String TEXT_1149 = ", null, ";
  protected final String TEXT_1150 = "isSetChange";
  protected final String TEXT_1151 = "old";
  protected final String TEXT_1152 = "ESet";
  protected final String TEXT_1153 = "));";
  protected final String TEXT_1154 = NL + "\t\tif (";
  protected final String TEXT_1155 = ") ";
  protected final String TEXT_1156 = " |= ";
  protected final String TEXT_1157 = "_EFLAG; else ";
  protected final String TEXT_1158 = " &= ~";
  protected final String TEXT_1159 = "_EFLAG;";
  protected final String TEXT_1160 = NL + "\t\t";
  protected final String TEXT_1161 = " = ";
  protected final String TEXT_1162 = " & ~";
  protected final String TEXT_1163 = "_EFLAG | ";
  protected final String TEXT_1164 = "_EFLAG_DEFAULT;";
  protected final String TEXT_1165 = NL + "\t\t";
  protected final String TEXT_1166 = " = ";
  protected final String TEXT_1167 = ";";
  protected final String TEXT_1168 = NL + "\t\t";
  protected final String TEXT_1169 = " &= ~";
  protected final String TEXT_1170 = "_ESETFLAG;";
  protected final String TEXT_1171 = NL + "\t\t";
  protected final String TEXT_1172 = "ESet = false;";
  protected final String TEXT_1173 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_1174 = "(this, ";
  protected final String TEXT_1175 = ".UNSET, ";
  protected final String TEXT_1176 = ", ";
  protected final String TEXT_1177 = "isSetChange ? old";
  protected final String TEXT_1178 = " : ";
  protected final String TEXT_1179 = "old";
  protected final String TEXT_1180 = ", ";
  protected final String TEXT_1181 = ", ";
  protected final String TEXT_1182 = "isSetChange";
  protected final String TEXT_1183 = "old";
  protected final String TEXT_1184 = "ESet";
  protected final String TEXT_1185 = "));";
  protected final String TEXT_1186 = NL + "\t\t((";
  protected final String TEXT_1187 = ".Internal)((";
  protected final String TEXT_1188 = ".Internal.Wrapper)get";
  protected final String TEXT_1189 = "()).featureMap()).clear(";
  protected final String TEXT_1190 = ");";
  protected final String TEXT_1191 = NL + "\t\t((";
  protected final String TEXT_1192 = ".Internal)get";
  protected final String TEXT_1193 = "()).clear(";
  protected final String TEXT_1194 = ");";
  protected final String TEXT_1195 = NL + "\t\t";
  protected final String TEXT_1196 = NL + "\t\t// TODO: implement this method to unset the '";
  protected final String TEXT_1197 = "' ";
  protected final String TEXT_1198 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1199 = NL + "\t}" + NL;
  protected final String TEXT_1200 = NL + "\t/**" + NL + "\t * Returns whether the value of the '{@link ";
  protected final String TEXT_1201 = "#";
  protected final String TEXT_1202 = " <em>";
  protected final String TEXT_1203 = "</em>}' ";
  protected final String TEXT_1204 = " is set.";
  protected final String TEXT_1205 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return whether the value of the '<em>";
  protected final String TEXT_1206 = "</em>' ";
  protected final String TEXT_1207 = " is set.";
  protected final String TEXT_1208 = NL + "\t * @see #unset";
  protected final String TEXT_1209 = "()";
  protected final String TEXT_1210 = NL + "\t * @see #";
  protected final String TEXT_1211 = "()";
  protected final String TEXT_1212 = NL + "\t * @see #set";
  protected final String TEXT_1213 = "(";
  protected final String TEXT_1214 = ")";
  protected final String TEXT_1215 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1216 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1217 = NL + "\tboolean isSet";
  protected final String TEXT_1218 = "();" + NL;
  protected final String TEXT_1219 = NL + "\tpublic boolean isSet";
  protected final String TEXT_1220 = "_";
  protected final String TEXT_1221 = "()" + NL + "\t{";
  protected final String TEXT_1222 = NL + "\t\treturn eDynamicIsSet(";
  protected final String TEXT_1223 = " - ESTATIC_FEATURE_COUNT";
  protected final String TEXT_1224 = ", ";
  protected final String TEXT_1225 = ");";
  protected final String TEXT_1226 = NL + "\t\treturn eIsSet(";
  protected final String TEXT_1227 = ");";
  protected final String TEXT_1228 = NL + "\t\treturn ";
  protected final String TEXT_1229 = "__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);";
  protected final String TEXT_1230 = NL + "\t\t";
  protected final String TEXT_1231 = " ";
  protected final String TEXT_1232 = " = (";
  protected final String TEXT_1233 = ")eVirtualGet(";
  protected final String TEXT_1234 = ");";
  protected final String TEXT_1235 = NL + "\t\treturn ";
  protected final String TEXT_1236 = " != null && ((";
  protected final String TEXT_1237 = ".Unsettable";
  protected final String TEXT_1238 = ")";
  protected final String TEXT_1239 = ").isSet();";
  protected final String TEXT_1240 = NL + "\t\treturn eVirtualIsSet(";
  protected final String TEXT_1241 = ");";
  protected final String TEXT_1242 = NL + "\t\treturn (";
  protected final String TEXT_1243 = " & ";
  protected final String TEXT_1244 = "_ESETFLAG) != 0;";
  protected final String TEXT_1245 = NL + "\t\treturn ";
  protected final String TEXT_1246 = "ESet;";
  protected final String TEXT_1247 = NL + "\t\treturn !((";
  protected final String TEXT_1248 = ".Internal)((";
  protected final String TEXT_1249 = ".Internal.Wrapper)get";
  protected final String TEXT_1250 = "()).featureMap()).isEmpty(";
  protected final String TEXT_1251 = ");";
  protected final String TEXT_1252 = NL + "\t\treturn !((";
  protected final String TEXT_1253 = ".Internal)get";
  protected final String TEXT_1254 = "()).isEmpty(";
  protected final String TEXT_1255 = ");";
  protected final String TEXT_1256 = NL + "\t\t";
  protected final String TEXT_1257 = NL + "\t\t// TODO: implement this method to return whether the '";
  protected final String TEXT_1258 = "' ";
  protected final String TEXT_1259 = " is set" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1260 = NL + "\t}" + NL;
  protected final String TEXT_1261 = NL + "\t/**" + NL + "\t * The cached validation expression for the '{@link #";
  protected final String TEXT_1262 = "(";
  protected final String TEXT_1263 = ") <em>";
  protected final String TEXT_1264 = "</em>}' invariant operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1265 = "(";
  protected final String TEXT_1266 = ")" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_1267 = " ";
  protected final String TEXT_1268 = "__EEXPRESSION = \"";
  protected final String TEXT_1269 = "\";";
  protected final String TEXT_1270 = NL;
  protected final String TEXT_1271 = NL + "\t/**" + NL + "\t * The cached invocation delegate for the '{@link #";
  protected final String TEXT_1272 = "(";
  protected final String TEXT_1273 = ") <em>";
  protected final String TEXT_1274 = "</em>}' operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1275 = "(";
  protected final String TEXT_1276 = ")" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_1277 = ".Internal.InvocationDelegate ";
  protected final String TEXT_1278 = "__EINVOCATION_DELEGATE = ((";
  protected final String TEXT_1279 = ".Internal)";
  protected final String TEXT_1280 = ").getInvocationDelegate();" + NL;
  protected final String TEXT_1281 = NL + "\t/**";
  protected final String TEXT_1282 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_1283 = NL + "\t * <!-- begin-model-doc -->";
  protected final String TEXT_1284 = NL + "\t * ";
  protected final String TEXT_1285 = NL + "\t * @param ";
  protected final String TEXT_1286 = NL + "\t *   ";
  protected final String TEXT_1287 = NL + "\t * @param ";
  protected final String TEXT_1288 = " ";
  protected final String TEXT_1289 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_1290 = NL + "\t * @model ";
  protected final String TEXT_1291 = NL + "\t *        ";
  protected final String TEXT_1292 = NL + "\t * @model";
  protected final String TEXT_1293 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1294 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1295 = NL + "\t";
  protected final String TEXT_1296 = " ";
  protected final String TEXT_1297 = "(";
  protected final String TEXT_1298 = ")";
  protected final String TEXT_1299 = ";" + NL;
  protected final String TEXT_1300 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1301 = NL + "\tpublic ";
  protected final String TEXT_1302 = " ";
  protected final String TEXT_1303 = "(";
  protected final String TEXT_1304 = ")";
  protected final String TEXT_1305 = NL + "\t{";
  protected final String TEXT_1306 = NL + "\t\t";
  protected final String TEXT_1307 = NL + "\t\treturn" + NL + "\t\t\t";
  protected final String TEXT_1308 = ".validate" + NL + "\t\t\t\t(";
  protected final String TEXT_1309 = "," + NL + "\t\t\t\t this," + NL + "\t\t\t\t ";
  protected final String TEXT_1310 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_1311 = "," + NL + "\t\t\t\t \"";
  protected final String TEXT_1312 = "\",";
  protected final String TEXT_1313 = NL + "\t\t\t\t ";
  protected final String TEXT_1314 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_1315 = "__EEXPRESSION," + NL + "\t\t\t\t ";
  protected final String TEXT_1316 = ".ERROR," + NL + "\t\t\t\t ";
  protected final String TEXT_1317 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t ";
  protected final String TEXT_1318 = ".";
  protected final String TEXT_1319 = ");";
  protected final String TEXT_1320 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// -> specify the condition that violates the invariant" + NL + "\t\t// -> verify the details of the diagnostic, including severity and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_1321 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_1322 = ".add" + NL + "\t\t\t\t\t(new ";
  protected final String TEXT_1323 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_1324 = ".ERROR," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1325 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1326 = ".";
  protected final String TEXT_1327 = "," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1328 = ".INSTANCE.getString(\"_UI_GenericInvariant_diagnostic\", new Object[] { \"";
  protected final String TEXT_1329 = "\", ";
  protected final String TEXT_1330 = ".getObjectLabel(this, ";
  protected final String TEXT_1331 = ") }),";
  protected final String TEXT_1332 = NL + "\t\t\t\t\t\t new Object [] { this }));" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_1333 = NL + "\t\ttry" + NL + "\t\t{";
  protected final String TEXT_1334 = NL + "\t\t\t";
  protected final String TEXT_1335 = "__EINVOCATION_DELEGATE.dynamicInvoke(this, ";
  protected final String TEXT_1336 = "new ";
  protected final String TEXT_1337 = ".UnmodifiableEList<Object>(";
  protected final String TEXT_1338 = ", ";
  protected final String TEXT_1339 = ")";
  protected final String TEXT_1340 = "null";
  protected final String TEXT_1341 = ");";
  protected final String TEXT_1342 = NL + "\t\t\treturn ";
  protected final String TEXT_1343 = "(";
  protected final String TEXT_1344 = "(";
  protected final String TEXT_1345 = ")";
  protected final String TEXT_1346 = "__EINVOCATION_DELEGATE.dynamicInvoke(this, ";
  protected final String TEXT_1347 = "new ";
  protected final String TEXT_1348 = ".UnmodifiableEList<Object>(";
  protected final String TEXT_1349 = ", ";
  protected final String TEXT_1350 = ")";
  protected final String TEXT_1351 = "null";
  protected final String TEXT_1352 = ")";
  protected final String TEXT_1353 = ").";
  protected final String TEXT_1354 = "()";
  protected final String TEXT_1355 = ";";
  protected final String TEXT_1356 = NL + "\t\t}" + NL + "\t\tcatch (";
  protected final String TEXT_1357 = " ite)" + NL + "\t\t{" + NL + "\t\t\tthrow new ";
  protected final String TEXT_1358 = "(ite);" + NL + "\t\t}";
  protected final String TEXT_1359 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1360 = NL + "\t}" + NL;
  protected final String TEXT_1361 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1362 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1363 = NL + "\t@Override";
  protected final String TEXT_1364 = NL + "\tpublic ";
  protected final String TEXT_1365 = " eInverseAdd(";
  protected final String TEXT_1366 = " otherEnd, int featureID, ";
  protected final String TEXT_1367 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1368 = ")" + NL + "\t\t{";
  protected final String TEXT_1369 = NL + "\t\t\tcase ";
  protected final String TEXT_1370 = ":";
  protected final String TEXT_1371 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1372 = "(";
  protected final String TEXT_1373 = ".InternalMapView";
  protected final String TEXT_1374 = ")";
  protected final String TEXT_1375 = "()).eMap()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1376 = NL + "\t\t\t\treturn (";
  protected final String TEXT_1377 = "()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1378 = NL + "\t\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1379 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1380 = "((";
  protected final String TEXT_1381 = ")otherEnd, msgs);";
  protected final String TEXT_1382 = NL + "\t\t\t\treturn eBasicSetContainer(otherEnd, ";
  protected final String TEXT_1383 = ", msgs);";
  protected final String TEXT_1384 = NL + "\t\t\t\t";
  protected final String TEXT_1385 = " ";
  protected final String TEXT_1386 = " = (";
  protected final String TEXT_1387 = ")eVirtualGet(";
  protected final String TEXT_1388 = ");";
  protected final String TEXT_1389 = NL + "\t\t\t\t";
  protected final String TEXT_1390 = " ";
  protected final String TEXT_1391 = " = ";
  protected final String TEXT_1392 = "basicGet";
  protected final String TEXT_1393 = "();";
  protected final String TEXT_1394 = NL + "\t\t\t\tif (";
  protected final String TEXT_1395 = " != null)";
  protected final String TEXT_1396 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1397 = ")";
  protected final String TEXT_1398 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_1399 = ", null, msgs);";
  protected final String TEXT_1400 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1401 = ")";
  protected final String TEXT_1402 = ").eInverseRemove(this, ";
  protected final String TEXT_1403 = ", ";
  protected final String TEXT_1404 = ".class, msgs);";
  protected final String TEXT_1405 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1406 = "((";
  protected final String TEXT_1407 = ")otherEnd, msgs);";
  protected final String TEXT_1408 = NL + "\t\t}";
  protected final String TEXT_1409 = NL + "\t\treturn super.eInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1410 = NL + "\t\treturn eDynamicInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1411 = NL + "\t}" + NL;
  protected final String TEXT_1412 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1413 = NL + "\t@Override";
  protected final String TEXT_1414 = NL + "\tpublic ";
  protected final String TEXT_1415 = " eInverseRemove(";
  protected final String TEXT_1416 = " otherEnd, int featureID, ";
  protected final String TEXT_1417 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1418 = ")" + NL + "\t\t{";
  protected final String TEXT_1419 = NL + "\t\t\tcase ";
  protected final String TEXT_1420 = ":";
  protected final String TEXT_1421 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1422 = ")((";
  protected final String TEXT_1423 = ".InternalMapView";
  protected final String TEXT_1424 = ")";
  protected final String TEXT_1425 = "()).eMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1426 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1427 = ")((";
  protected final String TEXT_1428 = ".Internal.Wrapper)";
  protected final String TEXT_1429 = "()).featureMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1430 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1431 = ")";
  protected final String TEXT_1432 = "()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1433 = NL + "\t\t\t\treturn eBasicSetContainer(null, ";
  protected final String TEXT_1434 = ", msgs);";
  protected final String TEXT_1435 = NL + "\t\t\t\treturn basicUnset";
  protected final String TEXT_1436 = "(msgs);";
  protected final String TEXT_1437 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1438 = "(null, msgs);";
  protected final String TEXT_1439 = NL + "\t\t}";
  protected final String TEXT_1440 = NL + "\t\treturn super.eInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1441 = NL + "\t\treturn eDynamicInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1442 = NL + "\t}" + NL;
  protected final String TEXT_1443 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1444 = NL + "\t@Override";
  protected final String TEXT_1445 = NL + "\tpublic ";
  protected final String TEXT_1446 = " eBasicRemoveFromContainerFeature(";
  protected final String TEXT_1447 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (eContainerFeatureID()";
  protected final String TEXT_1448 = ")" + NL + "\t\t{";
  protected final String TEXT_1449 = NL + "\t\t\tcase ";
  protected final String TEXT_1450 = ":" + NL + "\t\t\t\treturn eInternalContainer().eInverseRemove(this, ";
  protected final String TEXT_1451 = ", ";
  protected final String TEXT_1452 = ".class, msgs);";
  protected final String TEXT_1453 = NL + "\t\t}";
  protected final String TEXT_1454 = NL + "\t\treturn super.eBasicRemoveFromContainerFeature(msgs);";
  protected final String TEXT_1455 = NL + "\t\treturn eDynamicBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1456 = NL + "\t}" + NL;
  protected final String TEXT_1457 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1458 = NL + "\t@Override";
  protected final String TEXT_1459 = NL + "\tpublic Object eGet(int featureID, boolean resolve, boolean coreType)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1460 = ")" + NL + "\t\t{";
  protected final String TEXT_1461 = NL + "\t\t\tcase ";
  protected final String TEXT_1462 = ":";
  protected final String TEXT_1463 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1464 = "();";
  protected final String TEXT_1465 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1466 = "() ? Boolean.TRUE : Boolean.FALSE;";
  protected final String TEXT_1467 = NL + "\t\t\t\treturn new ";
  protected final String TEXT_1468 = "(";
  protected final String TEXT_1469 = "());";
  protected final String TEXT_1470 = NL + "\t\t\t\tif (resolve) return ";
  protected final String TEXT_1471 = "();" + NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1472 = "();";
  protected final String TEXT_1473 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1474 = ".InternalMapView";
  protected final String TEXT_1475 = ")";
  protected final String TEXT_1476 = "()).eMap();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1477 = "();";
  protected final String TEXT_1478 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1479 = "();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1480 = "().map();";
  protected final String TEXT_1481 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1482 = ".Internal.Wrapper)";
  protected final String TEXT_1483 = "()).featureMap();" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1484 = "();";
  protected final String TEXT_1485 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1486 = "();" + NL + "\t\t\t\treturn ((";
  protected final String TEXT_1487 = ".Internal)";
  protected final String TEXT_1488 = "()).getWrapper();";
  protected final String TEXT_1489 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1490 = "();";
  protected final String TEXT_1491 = NL + "\t\t}";
  protected final String TEXT_1492 = NL + "\t\treturn super.eGet(featureID, resolve, coreType);";
  protected final String TEXT_1493 = NL + "\t\treturn eDynamicGet(featureID, resolve, coreType);";
  protected final String TEXT_1494 = NL + "\t}" + NL;
  protected final String TEXT_1495 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1496 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1497 = NL + "\t@Override";
  protected final String TEXT_1498 = NL + "\tpublic void eSet(int featureID, Object newValue)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1499 = ")" + NL + "\t\t{";
  protected final String TEXT_1500 = NL + "\t\t\tcase ";
  protected final String TEXT_1501 = ":";
  protected final String TEXT_1502 = NL + "\t\t\t\t((";
  protected final String TEXT_1503 = ".Internal)((";
  protected final String TEXT_1504 = ".Internal.Wrapper)";
  protected final String TEXT_1505 = "()).featureMap()).set(newValue);";
  protected final String TEXT_1506 = NL + "\t\t\t\t((";
  protected final String TEXT_1507 = ".Internal)";
  protected final String TEXT_1508 = "()).set(newValue);";
  protected final String TEXT_1509 = NL + "\t\t\t\t((";
  protected final String TEXT_1510 = ".Setting)((";
  protected final String TEXT_1511 = ".InternalMapView";
  protected final String TEXT_1512 = ")";
  protected final String TEXT_1513 = "()).eMap()).set(newValue);";
  protected final String TEXT_1514 = NL + "\t\t\t\t((";
  protected final String TEXT_1515 = ".Setting)";
  protected final String TEXT_1516 = "()).set(newValue);";
  protected final String TEXT_1517 = NL + "\t\t\t\t";
  protected final String TEXT_1518 = "().clear();" + NL + "\t\t\t\t";
  protected final String TEXT_1519 = "().addAll((";
  protected final String TEXT_1520 = "<? extends ";
  protected final String TEXT_1521 = ">";
  protected final String TEXT_1522 = ")newValue);";
  protected final String TEXT_1523 = NL + "\t\t\t\tset";
  protected final String TEXT_1524 = "(((";
  protected final String TEXT_1525 = ")newValue).";
  protected final String TEXT_1526 = "());";
  protected final String TEXT_1527 = NL + "\t\t\t\tset";
  protected final String TEXT_1528 = "(";
  protected final String TEXT_1529 = "(";
  protected final String TEXT_1530 = ")";
  protected final String TEXT_1531 = "newValue);";
  protected final String TEXT_1532 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1533 = NL + "\t\t}";
  protected final String TEXT_1534 = NL + "\t\tsuper.eSet(featureID, newValue);";
  protected final String TEXT_1535 = NL + "\t\teDynamicSet(featureID, newValue);";
  protected final String TEXT_1536 = NL + "\t}" + NL;
  protected final String TEXT_1537 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1538 = NL + "\t@Override";
  protected final String TEXT_1539 = NL + "\tpublic void eUnset(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1540 = ")" + NL + "\t\t{";
  protected final String TEXT_1541 = NL + "\t\t\tcase ";
  protected final String TEXT_1542 = ":";
  protected final String TEXT_1543 = NL + "\t\t\t\t((";
  protected final String TEXT_1544 = ".Internal.Wrapper)";
  protected final String TEXT_1545 = "()).featureMap().clear();";
  protected final String TEXT_1546 = NL + "\t\t\t\t";
  protected final String TEXT_1547 = "().clear();";
  protected final String TEXT_1548 = NL + "\t\t\t\tunset";
  protected final String TEXT_1549 = "();";
  protected final String TEXT_1550 = NL + "\t\t\t\tset";
  protected final String TEXT_1551 = "((";
  protected final String TEXT_1552 = ")null);";
  protected final String TEXT_1553 = NL + "\t\t\t\t";
  protected final String TEXT_1554 = "__ESETTING_DELEGATE.dynamicUnset(this, null, 0);";
  protected final String TEXT_1555 = NL + "\t\t\t\tset";
  protected final String TEXT_1556 = "(";
  protected final String TEXT_1557 = ");";
  protected final String TEXT_1558 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1559 = NL + "\t\t}";
  protected final String TEXT_1560 = NL + "\t\tsuper.eUnset(featureID);";
  protected final String TEXT_1561 = NL + "\t\teDynamicUnset(featureID);";
  protected final String TEXT_1562 = NL + "\t}" + NL;
  protected final String TEXT_1563 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1564 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1565 = NL + "\t@Override";
  protected final String TEXT_1566 = NL + "\tpublic boolean eIsSet(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1567 = ")" + NL + "\t\t{";
  protected final String TEXT_1568 = NL + "\t\t\tcase ";
  protected final String TEXT_1569 = ":";
  protected final String TEXT_1570 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_1571 = "();";
  protected final String TEXT_1572 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1573 = "__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);";
  protected final String TEXT_1574 = NL + "\t\t\t\treturn !((";
  protected final String TEXT_1575 = ".Internal.Wrapper)";
  protected final String TEXT_1576 = "()).featureMap().isEmpty();";
  protected final String TEXT_1577 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1578 = " != null && !";
  protected final String TEXT_1579 = ".featureMap().isEmpty();";
  protected final String TEXT_1580 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1581 = " != null && !";
  protected final String TEXT_1582 = ".isEmpty();";
  protected final String TEXT_1583 = NL + "\t\t\t\t";
  protected final String TEXT_1584 = " ";
  protected final String TEXT_1585 = " = (";
  protected final String TEXT_1586 = ")eVirtualGet(";
  protected final String TEXT_1587 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1588 = " != null && !";
  protected final String TEXT_1589 = ".isEmpty();";
  protected final String TEXT_1590 = NL + "\t\t\t\treturn !";
  protected final String TEXT_1591 = "().isEmpty();";
  protected final String TEXT_1592 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_1593 = "();";
  protected final String TEXT_1594 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1595 = " != null;";
  protected final String TEXT_1596 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1597 = ") != null;";
  protected final String TEXT_1598 = NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1599 = "() != null;";
  protected final String TEXT_1600 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1601 = " != null;";
  protected final String TEXT_1602 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1603 = ") != null;";
  protected final String TEXT_1604 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1605 = "() != null;";
  protected final String TEXT_1606 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1607 = " & ";
  protected final String TEXT_1608 = "_EFLAG) != 0) != ";
  protected final String TEXT_1609 = ";";
  protected final String TEXT_1610 = NL + "\t\t\t\treturn (";
  protected final String TEXT_1611 = " & ";
  protected final String TEXT_1612 = "_EFLAG) != ";
  protected final String TEXT_1613 = "_EFLAG_DEFAULT;";
  protected final String TEXT_1614 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1615 = " != ";
  protected final String TEXT_1616 = ";";
  protected final String TEXT_1617 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1618 = ", ";
  protected final String TEXT_1619 = ") != ";
  protected final String TEXT_1620 = ";";
  protected final String TEXT_1621 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1622 = "() != ";
  protected final String TEXT_1623 = ";";
  protected final String TEXT_1624 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1625 = " == null ? ";
  protected final String TEXT_1626 = " != null : !";
  protected final String TEXT_1627 = ".equals(";
  protected final String TEXT_1628 = ");";
  protected final String TEXT_1629 = NL + "\t\t\t\t";
  protected final String TEXT_1630 = " ";
  protected final String TEXT_1631 = " = (";
  protected final String TEXT_1632 = ")eVirtualGet(";
  protected final String TEXT_1633 = ", ";
  protected final String TEXT_1634 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1635 = " == null ? ";
  protected final String TEXT_1636 = " != null : !";
  protected final String TEXT_1637 = ".equals(";
  protected final String TEXT_1638 = ");";
  protected final String TEXT_1639 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1640 = " == null ? ";
  protected final String TEXT_1641 = "() != null : !";
  protected final String TEXT_1642 = ".equals(";
  protected final String TEXT_1643 = "());";
  protected final String TEXT_1644 = NL + "\t\t}";
  protected final String TEXT_1645 = NL + "\t\treturn super.eIsSet(featureID);";
  protected final String TEXT_1646 = NL + "\t\treturn eDynamicIsSet(featureID);";
  protected final String TEXT_1647 = NL + "\t}" + NL;
  protected final String TEXT_1648 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1649 = NL + "\t@Override";
  protected final String TEXT_1650 = NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class";
  protected final String TEXT_1651 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1652 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1653 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (derivedFeatureID";
  protected final String TEXT_1654 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1655 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1656 = ": return ";
  protected final String TEXT_1657 = ";";
  protected final String TEXT_1658 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1659 = NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);" + NL + "\t}";
  protected final String TEXT_1660 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1661 = NL + "\t@Override";
  protected final String TEXT_1662 = NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class";
  protected final String TEXT_1663 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1664 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1665 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_1666 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1667 = ": return ";
  protected final String TEXT_1668 = ";";
  protected final String TEXT_1669 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1670 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1671 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID";
  protected final String TEXT_1672 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1673 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1674 = ": return ";
  protected final String TEXT_1675 = ";";
  protected final String TEXT_1676 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1677 = NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1678 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1679 = NL + "\t@Override";
  protected final String TEXT_1680 = NL + "\tpublic int eDerivedOperationID(int baseOperationID, Class";
  protected final String TEXT_1681 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1682 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1683 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID)" + NL + "\t\t\t{";
  protected final String TEXT_1684 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1685 = ": return ";
  protected final String TEXT_1686 = ";";
  protected final String TEXT_1687 = NL + "\t\t\t\tdefault: return super.eDerivedOperationID(baseOperationID, baseClass);" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1688 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1689 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID)" + NL + "\t\t\t{";
  protected final String TEXT_1690 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1691 = ": return ";
  protected final String TEXT_1692 = ";";
  protected final String TEXT_1693 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1694 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1695 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID";
  protected final String TEXT_1696 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1697 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1698 = ": return ";
  protected final String TEXT_1699 = ";";
  protected final String TEXT_1700 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1701 = NL + "\t\treturn super.eDerivedOperationID(baseOperationID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1702 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1703 = NL + "\t@Override";
  protected final String TEXT_1704 = NL + "\tprotected Object[] eVirtualValues()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_1705 = ";" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1706 = NL + "\t@Override";
  protected final String TEXT_1707 = NL + "\tprotected void eSetVirtualValues(Object[] newValues)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1708 = " = newValues;" + NL + "\t}" + NL;
  protected final String TEXT_1709 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1710 = NL + "\t@Override";
  protected final String TEXT_1711 = NL + "\tprotected int eVirtualIndexBits(int offset)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1712 = NL + "\t\t\tcase ";
  protected final String TEXT_1713 = " :" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1714 = ";";
  protected final String TEXT_1715 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1716 = NL + "\t@Override";
  protected final String TEXT_1717 = NL + "\tprotected void eSetVirtualIndexBits(int offset, int newIndexBits)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1718 = NL + "\t\t\tcase ";
  protected final String TEXT_1719 = " :" + NL + "\t\t\t\t";
  protected final String TEXT_1720 = " = newIndexBits;" + NL + "\t\t\t\tbreak;";
  protected final String TEXT_1721 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_1722 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1723 = NL + "\t@Override";
  protected final String TEXT_1724 = NL + "\t@SuppressWarnings(";
  protected final String TEXT_1725 = "\"unchecked\"";
  protected final String TEXT_1726 = "{\"rawtypes\", \"unchecked\" }";
  protected final String TEXT_1727 = ")";
  protected final String TEXT_1728 = NL + "\tpublic Object eInvoke(int operationID, ";
  protected final String TEXT_1729 = " arguments) throws ";
  protected final String TEXT_1730 = NL + "\t{" + NL + "\t\tswitch (operationID";
  protected final String TEXT_1731 = ")" + NL + "\t\t{";
  protected final String TEXT_1732 = NL + "\t\t\tcase ";
  protected final String TEXT_1733 = ":";
  protected final String TEXT_1734 = NL + "\t\t\t\ttry" + NL + "\t\t\t\t{";
  protected final String TEXT_1735 = NL + "\t\t\t\t";
  protected final String TEXT_1736 = "(";
  protected final String TEXT_1737 = "(";
  protected final String TEXT_1738 = "(";
  protected final String TEXT_1739 = ")";
  protected final String TEXT_1740 = "arguments.get(";
  protected final String TEXT_1741 = ")";
  protected final String TEXT_1742 = ").";
  protected final String TEXT_1743 = "()";
  protected final String TEXT_1744 = ", ";
  protected final String TEXT_1745 = ");" + NL + "\t\t\t\t";
  protected final String TEXT_1746 = "return null;";
  protected final String TEXT_1747 = NL + "\t\t\t\t";
  protected final String TEXT_1748 = "return ";
  protected final String TEXT_1749 = "new ";
  protected final String TEXT_1750 = "(";
  protected final String TEXT_1751 = "(";
  protected final String TEXT_1752 = "(";
  protected final String TEXT_1753 = "(";
  protected final String TEXT_1754 = ")";
  protected final String TEXT_1755 = "arguments.get(";
  protected final String TEXT_1756 = ")";
  protected final String TEXT_1757 = ").";
  protected final String TEXT_1758 = "()";
  protected final String TEXT_1759 = ", ";
  protected final String TEXT_1760 = ")";
  protected final String TEXT_1761 = ")";
  protected final String TEXT_1762 = ";";
  protected final String TEXT_1763 = NL + "\t\t\t\t}" + NL + "\t\t\t\tcatch (";
  protected final String TEXT_1764 = " throwable)" + NL + "\t\t\t\t{" + NL + "\t\t\t\t\tthrow new ";
  protected final String TEXT_1765 = "(throwable);" + NL + "\t\t\t\t}";
  protected final String TEXT_1766 = NL + "\t\t}";
  protected final String TEXT_1767 = NL + "\t\treturn super.eInvoke(operationID, arguments);";
  protected final String TEXT_1768 = NL + "\t\treturn eDynamicInvoke(operationID, arguments);";
  protected final String TEXT_1769 = NL + "\t}" + NL;
  protected final String TEXT_1770 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1771 = NL + "\t@Override";
  protected final String TEXT_1772 = NL + "\tpublic String toString()" + NL + "\t{" + NL + "\t\tif (eIsProxy()) return super.toString();" + NL + "" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());";
  protected final String TEXT_1773 = NL + "\t\tresult.append(\" (";
  protected final String TEXT_1774 = ": \");";
  protected final String TEXT_1775 = NL + "\t\tresult.append(\", ";
  protected final String TEXT_1776 = ": \");";
  protected final String TEXT_1777 = NL + "\t\tif (eVirtualIsSet(";
  protected final String TEXT_1778 = ")) result.append(eVirtualGet(";
  protected final String TEXT_1779 = ")); else result.append(\"<unset>\");";
  protected final String TEXT_1780 = NL + "\t\tif (";
  protected final String TEXT_1781 = "(";
  protected final String TEXT_1782 = " & ";
  protected final String TEXT_1783 = "_ESETFLAG) != 0";
  protected final String TEXT_1784 = "ESet";
  protected final String TEXT_1785 = ") result.append((";
  protected final String TEXT_1786 = " & ";
  protected final String TEXT_1787 = "_EFLAG) != 0); else result.append(\"<unset>\");";
  protected final String TEXT_1788 = NL + "\t\tif (";
  protected final String TEXT_1789 = "(";
  protected final String TEXT_1790 = " & ";
  protected final String TEXT_1791 = "_ESETFLAG) != 0";
  protected final String TEXT_1792 = "ESet";
  protected final String TEXT_1793 = ") result.append(";
  protected final String TEXT_1794 = "_EFLAG_VALUES[(";
  protected final String TEXT_1795 = " & ";
  protected final String TEXT_1796 = "_EFLAG) >>> ";
  protected final String TEXT_1797 = "_EFLAG_OFFSET]); else result.append(\"<unset>\");";
  protected final String TEXT_1798 = NL + "\t\tif (";
  protected final String TEXT_1799 = "(";
  protected final String TEXT_1800 = " & ";
  protected final String TEXT_1801 = "_ESETFLAG) != 0";
  protected final String TEXT_1802 = "ESet";
  protected final String TEXT_1803 = ") result.append(";
  protected final String TEXT_1804 = "); else result.append(\"<unset>\");";
  protected final String TEXT_1805 = NL + "\t\tresult.append(eVirtualGet(";
  protected final String TEXT_1806 = ", ";
  protected final String TEXT_1807 = "));";
  protected final String TEXT_1808 = NL + "\t\tresult.append((";
  protected final String TEXT_1809 = " & ";
  protected final String TEXT_1810 = "_EFLAG) != 0);";
  protected final String TEXT_1811 = NL + "\t\tresult.append(";
  protected final String TEXT_1812 = "_EFLAG_VALUES[(";
  protected final String TEXT_1813 = " & ";
  protected final String TEXT_1814 = "_EFLAG) >>> ";
  protected final String TEXT_1815 = "_EFLAG_OFFSET]);";
  protected final String TEXT_1816 = NL + "\t\tresult.append(";
  protected final String TEXT_1817 = ");";
  protected final String TEXT_1818 = NL + "\t\tresult.append(')');" + NL + "\t\treturn result.toString();" + NL + "\t}" + NL;
  protected final String TEXT_1819 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1820 = NL + "\t@";
  protected final String TEXT_1821 = NL + "\tprotected int hash = -1;" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int getHash()" + NL + "\t{" + NL + "\t\tif (hash == -1)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_1822 = " theKey = getKey();" + NL + "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + NL + "\t\t}" + NL + "\t\treturn hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setHash(int hash)" + NL + "\t{" + NL + "\t\tthis.hash = hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1823 = " getKey()" + NL + "\t{";
  protected final String TEXT_1824 = NL + "\t\treturn new ";
  protected final String TEXT_1825 = "(getTypedKey());";
  protected final String TEXT_1826 = NL + "\t\treturn getTypedKey();";
  protected final String TEXT_1827 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setKey(";
  protected final String TEXT_1828 = " key)" + NL + "\t{";
  protected final String TEXT_1829 = NL + "\t\tgetTypedKey().addAll(";
  protected final String TEXT_1830 = "(";
  protected final String TEXT_1831 = ")";
  protected final String TEXT_1832 = "key);";
  protected final String TEXT_1833 = NL + "\t\tsetTypedKey(key);";
  protected final String TEXT_1834 = NL + "\t\tsetTypedKey(((";
  protected final String TEXT_1835 = ")key).";
  protected final String TEXT_1836 = "());";
  protected final String TEXT_1837 = NL + "\t\tsetTypedKey((";
  protected final String TEXT_1838 = ")key);";
  protected final String TEXT_1839 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1840 = " getValue()" + NL + "\t{";
  protected final String TEXT_1841 = NL + "\t\treturn new ";
  protected final String TEXT_1842 = "(getTypedValue());";
  protected final String TEXT_1843 = NL + "\t\treturn getTypedValue();";
  protected final String TEXT_1844 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1845 = " setValue(";
  protected final String TEXT_1846 = " value)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1847 = " oldValue = getValue();";
  protected final String TEXT_1848 = NL + "\t\tgetTypedValue().clear();" + NL + "\t\tgetTypedValue().addAll(";
  protected final String TEXT_1849 = "(";
  protected final String TEXT_1850 = ")";
  protected final String TEXT_1851 = "value);";
  protected final String TEXT_1852 = NL + "\t\tsetTypedValue(value);";
  protected final String TEXT_1853 = NL + "\t\tsetTypedValue(((";
  protected final String TEXT_1854 = ")value).";
  protected final String TEXT_1855 = "());";
  protected final String TEXT_1856 = NL + "\t\tsetTypedValue((";
  protected final String TEXT_1857 = ")value);";
  protected final String TEXT_1858 = NL + "\t\treturn oldValue;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1859 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1860 = NL + "\tpublic ";
  protected final String TEXT_1861 = " getEMap()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1862 = " container = eContainer();" + NL + "\t\treturn container == null ? null : (";
  protected final String TEXT_1863 = ")container.eGet(eContainmentFeature());" + NL + "\t}" + NL;
  protected final String TEXT_1864 = NL + "} //";
  protected final String TEXT_1865 = NL;

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
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genPackage.getClassPackageName());
    stringBuffer.append(TEXT_8);
    }
    stringBuffer.append(TEXT_9);
    
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

    stringBuffer.append(TEXT_10);
    genModel.markImportLocation(stringBuffer, genPackage);
    if (isImplementation) { genClass.addClassPsuedoImports(); }
    stringBuffer.append(TEXT_11);
    if (isInterface) {
    stringBuffer.append(TEXT_12);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_13);
    if (genClass.hasDocumentation()) {
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genClass.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_15);
    }
    stringBuffer.append(TEXT_16);
    if (!genClass.getGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_17);
    for (GenFeature genFeature : genClass.getGenFeatures()) {
    if (!genFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_21);
    }
    }
    stringBuffer.append(TEXT_22);
    }
    stringBuffer.append(TEXT_23);
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(genClass.getClassifierAccessorName());
    stringBuffer.append(TEXT_26);
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genClass.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_27);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_28);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_29);
    }}
    if (genClass.needsRootExtendsInterfaceExtendsTag()) {
    stringBuffer.append(TEXT_30);
    stringBuffer.append(genModel.getImportedName(genModel.getRootExtendsInterface()));
    }
    stringBuffer.append(TEXT_31);
    //Class/interface.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_32);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_33);
    if (!genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_34);
    for (GenFeature genFeature : genClass.getImplementedGenFeatures()) {
    stringBuffer.append(TEXT_35);
    stringBuffer.append(genClass.getQualifiedClassName());
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_38);
    }
    stringBuffer.append(TEXT_39);
    }
    stringBuffer.append(TEXT_40);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_41);
    if (genClass.isAbstract()) {
    stringBuffer.append(TEXT_42);
    }
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genClass.getClassName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(genClass.getClassExtends());
    stringBuffer.append(genClass.getClassImplements());
    } else {
    stringBuffer.append(TEXT_44);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(genClass.getInterfaceExtends());
    }
    stringBuffer.append(TEXT_45);
    if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_46);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_48);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_49);
    }
    if (isImplementation && genModel.getDriverNumber() != null) {
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_51);
    stringBuffer.append(genModel.getDriverNumber());
    stringBuffer.append(TEXT_52);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_53);
    }
    if (isImplementation && genClass.isJavaIOSerializable()) {
    stringBuffer.append(TEXT_54);
    }
    if (isImplementation && genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_55);
    if (isGWT) {
    stringBuffer.append(TEXT_56);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_57);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_58);
    }
    { List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
    if (!eVirtualIndexBitFields.isEmpty()) {
    for (String eVirtualIndexBitField : eVirtualIndexBitFields) {
    stringBuffer.append(TEXT_59);
    if (isGWT) {
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_61);
    stringBuffer.append(eVirtualIndexBitField);
    stringBuffer.append(TEXT_62);
    }
    }
    }
    }
    if (isImplementation && genClass.isModelRoot() && genModel.isBooleanFlagsEnabled() && genModel.getBooleanFlagsReservedBits() == -1) {
    stringBuffer.append(TEXT_63);
    if (isGWT) {
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_65);
    stringBuffer.append(genModel.getBooleanFlagsField());
    stringBuffer.append(TEXT_66);
    }
    if (isImplementation && !genModel.isReflectiveDelegation()) {
    for (GenFeature genFeature : genClass.getReifiedGenFeatures()) {
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) { String rawListItemType = genFeature.getRawListItemType(genClass); int index = rawListItemType.indexOf('['); String head = rawListItemType; String tail = ""; if (index != -1) { head = rawListItemType.substring(0, index); tail = rawListItemType.substring(index); } 
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_70);
    if (genFeature.getQualifiedListItemType(genClass).contains("<") || genFeature.getArrayItemType(genClass).contains("<")) {
    stringBuffer.append(TEXT_71);
    }
    stringBuffer.append(TEXT_72);
    stringBuffer.append(rawListItemType);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_74);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_76);
    }
    }
    for (GenFeature genFeature : genClass.getDeclaredFieldGenFeatures()) {
    if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_77);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_79);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_81);
    if (isGWT) {
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_83);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_86);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_87);
    } else if (genFeature.isListType() || genFeature.isReferenceType()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_89);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_92);
    if (isGWT) {
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_94);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_95);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_96);
    }
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) { String rawListItemType = genFeature.getRawListItemType(genClass); int index = rawListItemType.indexOf('['); String head = rawListItemType; String tail = ""; if (index != -1) { head = rawListItemType.substring(0, index); tail = rawListItemType.substring(index); } 
    stringBuffer.append(TEXT_97);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_100);
    if (genFeature.getQualifiedListItemType(genClass).contains("<") || genFeature.getArrayItemType(genClass).contains("<")) {
    stringBuffer.append(TEXT_101);
    }
    stringBuffer.append(TEXT_102);
    stringBuffer.append(rawListItemType);
    stringBuffer.append(TEXT_103);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_104);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_105);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_106);
    }
    } else {
    if (genFeature.hasEDefault() && (!genFeature.isVolatile() || !genModel.isReflectiveDelegation() && (!genFeature.hasDelegateFeature() || !genFeature.isUnsettable()))) { String staticDefaultValue = genFeature.getStaticDefaultValue();
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_108);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_109);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_110);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_111);
    if (genModel.useGenerics() && genFeature.isListDataType() && genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_112);
    }
    stringBuffer.append(TEXT_113);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_114);
    stringBuffer.append(genFeature.getEDefault());
    if ("".equals(staticDefaultValue)) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getEcoreFeature().getDefaultValueLiteral());
    stringBuffer.append(TEXT_116);
    } else {
    stringBuffer.append(TEXT_117);
    stringBuffer.append(staticDefaultValue);
    stringBuffer.append(TEXT_118);
    stringBuffer.append(genModel.getNonNLS(staticDefaultValue));
    }
    stringBuffer.append(TEXT_119);
    }
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) { int flagIndex = genClass.getFlagIndex(genFeature);
    if (flagIndex > 31 && flagIndex % 32 == 0) {
    stringBuffer.append(TEXT_120);
    if (isGWT) {
    stringBuffer.append(TEXT_121);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_122);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_123);
    }
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_126);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_127);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_128);
    stringBuffer.append(flagIndex % 32);
    stringBuffer.append(TEXT_129);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_130);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_132);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_133);
    if (isJDK50) {
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_134);
    } else {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_135);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_136);
    }
    stringBuffer.append(TEXT_137);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_138);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_139);
    stringBuffer.append(genFeature.getTypeGenClassifier().getFormattedName());
    stringBuffer.append(TEXT_140);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_142);
    if (isJDK50) {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_143);
    } else {
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_145);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_146);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_147);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_148);
    }
    stringBuffer.append(TEXT_149);
    }
    stringBuffer.append(TEXT_150);
    stringBuffer.append(genClass.getFlagSize(genFeature) > 1 ? "s" : "");
    stringBuffer.append(TEXT_151);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_152);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_153);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_155);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_156);
    stringBuffer.append(genClass.getFlagMask(genFeature));
    stringBuffer.append(TEXT_157);
    if (genFeature.isEnumType()) {
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_158);
    } else {
    stringBuffer.append(flagIndex % 32);
    }
    stringBuffer.append(TEXT_159);
    } else {
    stringBuffer.append(TEXT_160);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_161);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_162);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_163);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_164);
    if (isGWT) {
    stringBuffer.append(TEXT_165);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_166);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_167);
    stringBuffer.append(genFeature.getSafeName());
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_168);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_169);
    }
    }
    }
    if (genClass.isESetField(genFeature)) {
    if (genClass.isESetFlag(genFeature)) { int flagIndex = genClass.getESetFlagIndex(genFeature);
    if (flagIndex > 31 && flagIndex % 32 == 0) {
    stringBuffer.append(TEXT_170);
    if (isGWT) {
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_172);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_173);
    }
    stringBuffer.append(TEXT_174);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_175);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_177);
    stringBuffer.append(flagIndex % 32 );
    stringBuffer.append(TEXT_178);
    } else {
    stringBuffer.append(TEXT_179);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_180);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_181);
    if (isGWT) {
    stringBuffer.append(TEXT_182);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_183);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_184);
    }
    }
    //Class/declaredFieldGenFeature.override.javajetinc
    }
    }
    if (isImplementation && genClass.hasOffsetCorrection() && !genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_185);
    stringBuffer.append(genClass.getOffsetCorrectionField(null));
    stringBuffer.append(TEXT_186);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_187);
    stringBuffer.append(genClass.getImplementedGenFeatures().get(0).getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_188);
    stringBuffer.append(genClass.getQualifiedFeatureID(genClass.getImplementedGenFeatures().get(0)));
    stringBuffer.append(TEXT_189);
    }
    if (isImplementation && !genModel.isReflectiveDelegation()) {
    for (GenFeature genFeature : genClass.getImplementedGenFeatures()) { GenFeature reverseFeature = genFeature.getReverse();
    if (reverseFeature != null && reverseFeature.getGenClass().hasOffsetCorrection()) {
    stringBuffer.append(TEXT_190);
    stringBuffer.append(genClass.getOffsetCorrectionField(genFeature));
    stringBuffer.append(TEXT_191);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_192);
    stringBuffer.append(reverseFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_193);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_194);
    }
    }
    }
    if (genModel.isOperationReflection() && isImplementation && genClass.hasOffsetCorrection() && !genClass.getImplementedGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_195);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_196);
    stringBuffer.append(genClass.getImplementedGenOperations().get(0).getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_197);
    stringBuffer.append(genClass.getQualifiedOperationID(genClass.getImplementedGenOperations().get(0)));
    stringBuffer.append(TEXT_198);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_199);
    if (genModel.isPublicConstructors()) {
    stringBuffer.append(TEXT_200);
    } else {
    stringBuffer.append(TEXT_201);
    }
    stringBuffer.append(TEXT_202);
    stringBuffer.append(genClass.getClassName());
    stringBuffer.append(TEXT_203);
    
		// *********************************
		//  VirSat Specific Code Generation
		// *********************************
		if (hasUUID) {
    stringBuffer.append(TEXT_204);
    }
    for (GenFeature genFeature : genClass.getFlagGenFeaturesWithDefault()) {
    stringBuffer.append(TEXT_205);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_206);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_207);
    if (!genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_208);
    }
    stringBuffer.append(TEXT_209);
    }
    stringBuffer.append(TEXT_210);
    if (!genClass.isAbstract()) {
    stringBuffer.append(TEXT_211);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_212);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_213);
    stringBuffer.append(genModel.getImportedName("de.dlr.sc.virsat.model.dvlm.util.DVLMUnresolvedReferenceException"));
    stringBuffer.append(TEXT_214);
    }
    stringBuffer.append(TEXT_215);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_216);
    }
    stringBuffer.append(TEXT_217);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClass"));
    stringBuffer.append(TEXT_218);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_219);
    }
    if (isImplementation && (genModel.getFeatureDelegation() == GenDelegationKind.REFLECTIVE_LITERAL || genModel.isDynamicDelegation()) && (genClass.getClassExtendsGenClass() == null || (genClass.getClassExtendsGenClass().getGenModel().getFeatureDelegation() != GenDelegationKind.REFLECTIVE_LITERAL && !genClass.getClassExtendsGenClass().getGenModel().isDynamicDelegation()))) {
    if (genClass.hasStaticFeatures()) {
    stringBuffer.append(TEXT_220);
    stringBuffer.append(genClass.getClassExtendsGenClass() == null ? 0 : genClass.getClassExtendsGenClass().getAllGenFeatures().size());
    stringBuffer.append(TEXT_221);
    }
    stringBuffer.append(TEXT_222);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_223);
    }
    stringBuffer.append(TEXT_224);
    stringBuffer.append(genClass.getClassExtendsGenClass() == null ? "0" : genClass.hasStaticFeatures() ? "ESTATIC_FEATURE_COUNT" : Integer.toString(genClass.getClassExtendsGenClass().getAllGenFeatures().size()));
    stringBuffer.append(TEXT_225);
    }
    //Class/reflectiveDelegation.override.javajetinc
    if (isImplementation) {
    new Runnable() { public void run() { GenClass classExtendsGenClass = genClass.getClassExtendsGenClass(); List<GenFeature> classExtendsAllGenFeatures = classExtendsGenClass == null? Collections.<GenFeature>emptyList() : classExtendsGenClass.getAllGenFeatures();
    for (GenFeature genFeature : genClass.getReifiedGenFeatures()) {
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) { String arrayElementType = genFeature.getArrayItemType(genClass);
    stringBuffer.append(TEXT_226);
    if (genModel.useGenerics() && CodeGenUtil.isUncheckedCast(arrayElementType)) {
    stringBuffer.append(TEXT_227);
    }
    if (classExtendsAllGenFeatures.contains(genFeature)) {
    stringBuffer.append(TEXT_228);
    }
    stringBuffer.append(TEXT_229);
    stringBuffer.append(arrayElementType);
    stringBuffer.append(TEXT_230);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_231);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_232);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_233);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_234);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_235);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_236);
    } else {
    stringBuffer.append(TEXT_237);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_238);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_239);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_240);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_241);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_242);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_243);
    }
    stringBuffer.append(TEXT_244);
    stringBuffer.append(arrayElementType);
    stringBuffer.append(TEXT_245);
    }
    if (genFeature.isGet() && genFeature.isListType()) {
    stringBuffer.append(TEXT_246);
    if (isJDK50) { //Class/getGenFeature.annotations.insert.javajetinc
    }
    if (genFeature.isListType() && genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_247);
    }
    if (classExtendsAllGenFeatures.contains(genFeature)) {
    stringBuffer.append(TEXT_248);
    }
    stringBuffer.append(TEXT_249);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_250);
    stringBuffer.append(genFeature.getGetAccessor());
    if (genClass.hasCollidingGetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_251);
    }
    stringBuffer.append(TEXT_252);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_253);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_254);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_255);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_256);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_257);
    }
    stringBuffer.append(TEXT_258);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_259);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_261);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_262);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_263);
    } else {
    stringBuffer.append(TEXT_264);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_265);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_266);
    }
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_268);
    }
    if (!genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_269);
    if (isJDK50) { //Class/basicSetGenFeature.annotations.insert.javajetinc
    }
    if (classExtendsAllGenFeatures.contains(genFeature)) {
    stringBuffer.append(TEXT_270);
    }
    stringBuffer.append(TEXT_271);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_272);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_273);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_274);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_275);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_276);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_277);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_278);
    }
    if (genFeature.isSet() && !(!genModel.isReflectiveDelegation() && genFeature.isBasicSet())) {
    stringBuffer.append(TEXT_279);
    if (isJDK50) { //Class/setGenFeature.annotations.insert.javajetinc
    }
    { GenOperation setAccessorOperation = genClass.getSetAccessorOperation(genFeature);
    if (classExtendsAllGenFeatures.contains(genFeature)) {
    stringBuffer.append(TEXT_280);
    }
    stringBuffer.append(TEXT_281);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_282);
    }
    stringBuffer.append(TEXT_283);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_284);
    stringBuffer.append(setAccessorOperation == null ? "new" + genFeature.getCapName() : setAccessorOperation.getGenParameters().get(0).getName());
    stringBuffer.append(TEXT_285);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_286);
    }
    stringBuffer.append(TEXT_287);
    stringBuffer.append(setAccessorOperation == null ? "new" + genFeature.getCapName() : setAccessorOperation.getGenParameters().get(0).getName());
    stringBuffer.append(TEXT_288);
    }
    }
    //Class/genFeatureReified.override.javajetinc
    }}}.run();}
    new Runnable() { public void run() {
    for (GenFeature genFeature : (isImplementation ? genClass.getImplementedGenFeatures() : genClass.getDeclaredGenFeatures())) {
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) { String arrayElementType = genFeature.getArrayItemType(genClass);
    stringBuffer.append(TEXT_289);
    if (!isImplementation) {
    stringBuffer.append(TEXT_290);
    stringBuffer.append(arrayElementType);
    stringBuffer.append(TEXT_291);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_292);
    } else {
    if (genModel.useGenerics() && CodeGenUtil.isUncheckedCast(arrayElementType)) {
    stringBuffer.append(TEXT_293);
    }
    stringBuffer.append(TEXT_294);
    stringBuffer.append(arrayElementType);
    stringBuffer.append(TEXT_295);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_296);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_297);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_298);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_299);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_300);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_301);
    } else {
    stringBuffer.append(TEXT_302);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_303);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_304);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_305);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_306);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_307);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_308);
    }
    stringBuffer.append(TEXT_309);
    stringBuffer.append(arrayElementType);
    stringBuffer.append(TEXT_310);
    }
    stringBuffer.append(TEXT_311);
    if (!isImplementation) {
    stringBuffer.append(TEXT_312);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_313);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_314);
    } else {
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_316);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_317);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_318);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_319);
    }
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_320);
    }
    stringBuffer.append(TEXT_321);
    if (!isImplementation) {
    stringBuffer.append(TEXT_322);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_323);
    } else {
    stringBuffer.append(TEXT_324);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_325);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_326);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_327);
    } else {
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_329);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_330);
    }
    stringBuffer.append(TEXT_331);
    }
    stringBuffer.append(TEXT_332);
    if (!isImplementation) {
    stringBuffer.append(TEXT_333);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_334);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_335);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_336);
    } else {
    stringBuffer.append(TEXT_337);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_338);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_340);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_341);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_342);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_343);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_344);
    }
    stringBuffer.append(TEXT_345);
    if (!isImplementation) {
    stringBuffer.append(TEXT_346);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_347);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_348);
    } else {
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_350);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_351);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_352);
    }
    }
    if (genFeature.isGet() && (isImplementation || !genFeature.isSuppressedGetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_353);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_354);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_355);
    if (genFeature.isListType() && genFeature.getEcoreFeature().getEGenericType().getETypeParameter() == null) {
    if (genFeature.isMapType()) { GenFeature keyFeature = genFeature.getMapEntryTypeGenClass().getMapEntryKeyFeature(); GenFeature valueFeature = genFeature.getMapEntryTypeGenClass().getMapEntryValueFeature(); 
    stringBuffer.append(TEXT_356);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_357);
    stringBuffer.append(keyFeature.getQualifiedListItemType(genClass));
    stringBuffer.append(TEXT_358);
    } else {
    stringBuffer.append(TEXT_359);
    stringBuffer.append(keyFeature.getType(genClass));
    stringBuffer.append(TEXT_360);
    }
    stringBuffer.append(TEXT_361);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_362);
    stringBuffer.append(valueFeature.getQualifiedListItemType(genClass));
    stringBuffer.append(TEXT_363);
    } else {
    stringBuffer.append(TEXT_364);
    stringBuffer.append(valueFeature.getType(genClass));
    stringBuffer.append(TEXT_365);
    }
    stringBuffer.append(TEXT_366);
    } else if (!genFeature.isWrappedFeatureMapType() && !(genModel.isSuppressEMFMetaData() && "org.eclipse.emf.ecore.EObject".equals(genFeature.getQualifiedListItemType(genClass)))) {
String typeName = genFeature.getQualifiedListItemType(genClass); String head = typeName; String tail = ""; int index = typeName.indexOf('<'); if (index == -1) { index = typeName.indexOf('['); } 
if (index != -1) { head = typeName.substring(0, index); tail = typeName.substring(index).replaceAll("<", "&lt;"); }

    stringBuffer.append(TEXT_367);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_368);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_369);
    }
    } else if (genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_370);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_371);
    }
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_372);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    stringBuffer.append(TEXT_373);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_374);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_375);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    stringBuffer.append(TEXT_376);
    stringBuffer.append(reverseGenFeature.getFormattedName());
    stringBuffer.append(TEXT_377);
    }
    }
    stringBuffer.append(TEXT_378);
    if (!genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_379);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_380);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_381);
    }
    stringBuffer.append(TEXT_382);
    if (genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_383);
    stringBuffer.append(genFeature.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_384);
    }
    stringBuffer.append(TEXT_385);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_386);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_387);
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_388);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_389);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_390);
    }
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_391);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_392);
    }
    }
    if (genFeature.isChangeable() && !genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_393);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_394);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_395);
    }
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_396);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_397);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_398);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_399);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_400);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    }
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genFeature.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_401);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_402);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_403);
    }}
    stringBuffer.append(TEXT_404);
    //Class/getGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_405);
    if (isJDK50) { //Class/getGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_406);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_407);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_408);
    } else {
    if (genModel.useGenerics() && ((genFeature.isContainer() || genFeature.isResolveProxies()) && !genFeature.isListType() && !(genModel.isReflectiveDelegation() && genModel.isDynamicDelegation()) && genFeature.isUncheckedCast(genClass) || genFeature.isListType() && !genFeature.isFeatureMapType() && (genModel.isReflectiveDelegation() || genModel.isVirtualDelegation() || genModel.isDynamicDelegation()) || genFeature.isListDataType() && genFeature.hasDelegateFeature() || genFeature.isListType() && genFeature.hasSettingDelegate())) {
    stringBuffer.append(TEXT_409);
    }
    stringBuffer.append(TEXT_410);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_411);
    stringBuffer.append(genFeature.getGetAccessor());
    if (genClass.hasCollidingGetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_412);
    }
    stringBuffer.append(TEXT_413);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_414);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_415);
    }
    stringBuffer.append(TEXT_416);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_417);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (genClass.hasStaticFeatures()){
    stringBuffer.append(TEXT_418);
    }
    stringBuffer.append(TEXT_419);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_420);
    stringBuffer.append(!genFeature.isEffectiveSuppressEMFTypes());
    stringBuffer.append(TEXT_421);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_422);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_423);
    }
    stringBuffer.append(TEXT_424);
    } else if (genModel.isReflectiveDelegation()) {
    if (genFeature.hasGetterBody()) {
    stringBuffer.append(TEXT_425);
    stringBuffer.append(genFeature.getGetterBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_426);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_427);
    }
    stringBuffer.append(TEXT_428);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_429);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_430);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_431);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_432);
    }
    stringBuffer.append(TEXT_433);
    }
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_434);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_435);
    }
    stringBuffer.append(TEXT_436);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_437);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_438);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_439);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_440);
    }
    stringBuffer.append(TEXT_441);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_442);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_443);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_444);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_445);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_446);
    }
    stringBuffer.append(TEXT_447);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_448);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_449);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_450);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_451);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_452);
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
    stringBuffer.append(TEXT_453);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_454);
    stringBuffer.append(ecoreListConstructor);
    stringBuffer.append(TEXT_455);
     } else { 
    stringBuffer.append(TEXT_456);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_457);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_458);
    }
    stringBuffer.append(TEXT_459);
    }
    stringBuffer.append(TEXT_460);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_461);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_462);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_463);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_464);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_465);
    } else {
    stringBuffer.append(TEXT_466);
    }
    stringBuffer.append(TEXT_467);
    } else {
    if (genFeature.isResolveProxies()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_468);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_469);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_470);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_471);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_472);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_473);
    }
    stringBuffer.append(TEXT_474);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_475);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_476);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_477);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_478);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_479);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_480);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_481);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
    stringBuffer.append(TEXT_482);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_483);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_484);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_485);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_486);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_487);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_488);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_489);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_490);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_491);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_492);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_493);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_494);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_495);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_496);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_497);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_498);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_499);
    }
    stringBuffer.append(TEXT_500);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_501);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_502);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_503);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_504);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_505);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_506);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_507);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_508);
    }
    stringBuffer.append(TEXT_509);
    } else if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_510);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_511);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_512);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_513);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_514);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_515);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_516);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_517);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_518);
    }
    stringBuffer.append(TEXT_519);
    }
    if (!genFeature.isResolveProxies() && genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_520);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_521);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_522);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_523);
    } else if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_524);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_525);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_526);
    } else {
    stringBuffer.append(TEXT_527);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_528);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_529);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_530);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_531);
    }
    } else {
    stringBuffer.append(TEXT_532);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_533);
    }
    }
    } else {//volatile
    if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_534);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_535);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_536);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_537);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_538);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_539);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
    stringBuffer.append(TEXT_540);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_541);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_542);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_543);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (genFeature.isFeatureMapType()) {
    String featureMapEntryTemplateArgument = isJDK50 ? "<" + genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap") + ".Entry>" : "";
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_544);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
    stringBuffer.append(TEXT_545);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_546);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_547);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_548);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_549);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_550);
    } else {
    stringBuffer.append(TEXT_551);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_552);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_553);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_554);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_555);
    }
    } else if (genFeature.isListType()) {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_556);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_557);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_558);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_559);
    } else {
    stringBuffer.append(TEXT_560);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_561);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_562);
    }
    } else {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_563);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_564);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_565);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_566);
    }
    stringBuffer.append(TEXT_567);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_568);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_569);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_570);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_571);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_572);
    }
    stringBuffer.append(TEXT_573);
    } else {
    stringBuffer.append(TEXT_574);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_575);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_576);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_577);
    }
    stringBuffer.append(TEXT_578);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_579);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_580);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_581);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_582);
    }
    stringBuffer.append(TEXT_583);
    }
    }
    } else if (genClass.getGetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_584);
    stringBuffer.append(genClass.getGetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else if (genFeature.hasGetterBody()) {
    stringBuffer.append(TEXT_585);
    stringBuffer.append(genFeature.getGetterBody(genModel.getIndentation(stringBuffer)));
    } else if (genFeature.getGetAccessor().equals(INTERFACE_NAME_QUALIFIEDNAME_GETTER)) {
    stringBuffer.append(TEXT_586);
    stringBuffer.append(genModel.getImportedName("de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper"));
    stringBuffer.append(TEXT_587);
    } else {
    stringBuffer.append(TEXT_588);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_589);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_590);
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_591);
    if (genFeature.isMapType()) {
    stringBuffer.append(TEXT_592);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_593);
    } else {
    stringBuffer.append(TEXT_594);
    }
    stringBuffer.append(TEXT_595);
    }
    stringBuffer.append(TEXT_596);
    //Class/getGenFeature.todo.override.javajetinc
    }
    }
    stringBuffer.append(TEXT_597);
    }
    //Class/getGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicGet()) {
    stringBuffer.append(TEXT_598);
    if (isJDK50) { //Class/basicGetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_599);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_600);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_601);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_602);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_603);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (genClass.hasStaticFeatures()){
    stringBuffer.append(TEXT_604);
    }
    stringBuffer.append(TEXT_605);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_606);
    stringBuffer.append(!genFeature.isEffectiveSuppressEMFTypes());
    stringBuffer.append(TEXT_607);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_608);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_609);
    }
    stringBuffer.append(TEXT_610);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_611);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_612);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_613);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_614);
    }
    stringBuffer.append(TEXT_615);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_616);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_617);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_618);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_619);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_620);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_621);
    } else {
    stringBuffer.append(TEXT_622);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_623);
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_624);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_625);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_626);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_627);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_628);
    } else {
    stringBuffer.append(TEXT_629);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_630);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_631);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_632);
    }
    } else if (genFeature.hasGetterBody()) {
    stringBuffer.append(TEXT_633);
    stringBuffer.append(genFeature.getGetterBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_634);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_635);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_636);
    //Class/basicGetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_637);
    //Class/basicGetGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_638);
    if (isJDK50) { //Class/basicSetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_639);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_640);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_641);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_642);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_643);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_644);
    if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_645);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_646);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_647);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_648);
    stringBuffer.append(TEXT_649);
    } else if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_650);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_651);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_652);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_653);
    stringBuffer.append(TEXT_654);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_655);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_656);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_657);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_658);
    } else {
    stringBuffer.append(TEXT_659);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_660);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_661);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_662);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_663);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_664);
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_665);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_666);
    }
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_667);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_668);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_669);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_670);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_671);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_672);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_673);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_674);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_675);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_676);
    }
    stringBuffer.append(TEXT_677);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_678);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_679);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_680);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_681);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_682);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_683);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_684);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_685);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_686);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_687);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_688);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_689);
    } else {
    stringBuffer.append(TEXT_690);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_691);
    }
    stringBuffer.append(TEXT_692);
    } else {
    stringBuffer.append(TEXT_693);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_694);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_695);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_696);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_697);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_698);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_699);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_700);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_701);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_702);
    }
    stringBuffer.append(TEXT_703);
    }
    stringBuffer.append(TEXT_704);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_705);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_706);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_707);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_708);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_709);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_710);
    } else {
    stringBuffer.append(TEXT_711);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_712);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_713);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_714);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_715);
    }
    } else {
    stringBuffer.append(TEXT_716);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_717);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_718);
    //Class/basicSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_719);
    //Class/basicSetGenFeature.override.javajetinc
    }
    if (genFeature.isSet() && (isImplementation || !genFeature.isSuppressedSetVisibility())) {
    if (isInterface) { 
    stringBuffer.append(TEXT_720);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_721);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_722);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_723);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_724);
    stringBuffer.append(TEXT_725);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_726);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_727);
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_728);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_729);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_730);
    }
    if (!genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_731);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_732);
    }
    }
    stringBuffer.append(TEXT_733);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_734);
    //Class/setGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_735);
    if (isJDK50) { //Class/setGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) { 
    stringBuffer.append(TEXT_736);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_737);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_738);
    } else { GenOperation setAccessorOperation = genClass.getSetAccessorOperation(genFeature);
    stringBuffer.append(TEXT_739);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_740);
    }
    stringBuffer.append(TEXT_741);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_742);
    stringBuffer.append(setAccessorOperation == null ? "new" + genFeature.getCapName() : setAccessorOperation.getGenParameters().get(0).getName());
    stringBuffer.append(TEXT_743);
    if (CLASS_NAME_REFERENCE_PROPERTY_INSTANCE.equals(genClass.getName()) && genFeature.getAccessorName().equals("Reference")) {
    stringBuffer.append(TEXT_744);
    stringBuffer.append(genModel.getImportedName("de.dlr.sc.virsat.model.dvlm.util.DVLMReferenceCheck"));
    stringBuffer.append(TEXT_745);
    stringBuffer.append(setAccessorOperation == null ? "new" + genFeature.getCapName() : setAccessorOperation.getGenParameters().get(0).getName());
    stringBuffer.append(TEXT_746);
    }
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_747);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (genClass.hasStaticFeatures()){
    stringBuffer.append(TEXT_748);
    }
    stringBuffer.append(TEXT_749);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_750);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_751);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_752);
    }
    stringBuffer.append(TEXT_753);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_754);
    }
    stringBuffer.append(TEXT_755);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_756);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_757);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_758);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_759);
    }
    stringBuffer.append(TEXT_760);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_761);
    }
    stringBuffer.append(TEXT_762);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_763);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_764);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_765);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_766);
    }
    stringBuffer.append(TEXT_767);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_768);
    }
    stringBuffer.append(TEXT_769);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isContainer()) { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_770);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_771);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_772);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_773);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreUtil"));
    stringBuffer.append(TEXT_774);
    stringBuffer.append(genFeature.getEObjectCast());
    stringBuffer.append(TEXT_775);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_776);
    stringBuffer.append(genModel.getImportedName("java.lang.IllegalArgumentException"));
    stringBuffer.append(TEXT_777);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_778);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_779);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_780);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_781);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_782);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_783);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_784);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_785);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_786);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_787);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_788);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_789);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_790);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_791);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_792);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_793);
    }
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_794);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_795);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_796);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_797);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_798);
    }
    stringBuffer.append(TEXT_799);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_800);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_801);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_802);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_803);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_804);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_805);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_806);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_807);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_808);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_809);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_810);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_811);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_812);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_813);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_814);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_815);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_816);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_817);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_818);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_819);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_820);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_821);
    }
    stringBuffer.append(TEXT_822);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_823);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_824);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_825);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_826);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_827);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_828);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_829);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_830);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_831);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_832);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_833);
    }
    stringBuffer.append(TEXT_834);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_835);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_836);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_837);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_838);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_839);
    }
    stringBuffer.append(TEXT_840);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_841);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_842);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_843);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_844);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_845);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_846);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_847);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_848);
    }
    stringBuffer.append(TEXT_849);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_850);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_851);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_852);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_853);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_854);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_855);
    }
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_856);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_857);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_858);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_859);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_860);
    } else {
    stringBuffer.append(TEXT_861);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_862);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_863);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_864);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_865);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_866);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_867);
    }
    }
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_868);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_869);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_870);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_871);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_872);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_873);
    } else {
    stringBuffer.append(TEXT_874);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_875);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_876);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_877);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_878);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_879);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_880);
    if (isJDK50) {
    stringBuffer.append(TEXT_881);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_882);
    } else {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_883);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_884);
    }
    stringBuffer.append(TEXT_885);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_886);
    }
    } else {
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_887);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_888);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_889);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_890);
    }
    }
    if (genFeature.isEnumType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_891);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_892);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_893);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_894);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_895);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_896);
    } else {
    stringBuffer.append(TEXT_897);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_898);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_899);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_900);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_901);
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_902);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_903);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_904);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_905);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_906);
    } else {
    stringBuffer.append(TEXT_907);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_908);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_909);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_910);
    }
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_911);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_912);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_913);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_914);
    }
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_915);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_916);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_917);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_918);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_919);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_920);
    }
    stringBuffer.append(TEXT_921);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_922);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_923);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_924);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_925);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_926);
    }
    stringBuffer.append(TEXT_927);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_928);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_929);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_930);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_931);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_932);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_933);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_934);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_935);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_936);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_937);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_938);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_939);
    } else {
    stringBuffer.append(TEXT_940);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_941);
    }
    stringBuffer.append(TEXT_942);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_943);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_944);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_945);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_946);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_947);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_948);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_949);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_950);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_951);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_952);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_953);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_954);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_955);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_956);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_957);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_958);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_959);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_960);
    }
    stringBuffer.append(TEXT_961);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_962);
    }
    stringBuffer.append(TEXT_963);
    } else {
    stringBuffer.append(TEXT_964);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_965);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_966);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_967);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_968);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_969);
    }
    stringBuffer.append(TEXT_970);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_971);
    }
    stringBuffer.append(TEXT_972);
    }
    } else if (setAccessorOperation != null) {
    stringBuffer.append(TEXT_973);
    stringBuffer.append(setAccessorOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_974);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_975);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_976);
    //Class/setGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_977);
    }
    //Class/setGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicUnset()) {
    stringBuffer.append(TEXT_978);
    if (isJDK50) { //Class/basicUnsetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_979);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_980);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_981);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_982);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_983);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_984);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_985);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_986);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_987);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_988);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_989);
    }
    stringBuffer.append(TEXT_990);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_991);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_992);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_993);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_994);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_995);
    }
    stringBuffer.append(TEXT_996);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_997);
    }
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_998);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_999);
    }
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1000);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1001);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1002);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1003);
    }
    stringBuffer.append(TEXT_1004);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1005);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1006);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1007);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1008);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1009);
    }
    stringBuffer.append(TEXT_1010);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1011);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1012);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_1013);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_1014);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_1015);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1016);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1017);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1018);
    } else {
    stringBuffer.append(TEXT_1019);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_1020);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1021);
    } else {
    stringBuffer.append(TEXT_1022);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1023);
    }
    stringBuffer.append(TEXT_1024);
    }
    } else {
    stringBuffer.append(TEXT_1025);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1026);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1027);
    //Class/basicUnsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1028);
    //Class.basicUnsetGenFeature.override.javajetinc
    }
    if (genFeature.isUnset() && (isImplementation || !genFeature.isSuppressedUnsetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_1029);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_1030);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1031);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1032);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1033);
    stringBuffer.append(TEXT_1034);
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_1035);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1036);
    }
    stringBuffer.append(TEXT_1037);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1038);
    if (!genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_1039);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1040);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_1041);
    }
    stringBuffer.append(TEXT_1042);
    //Class/unsetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1043);
    if (isJDK50) { //Class/unsetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1044);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1045);
    } else {
    stringBuffer.append(TEXT_1046);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingUnsetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_1047);
    }
    stringBuffer.append(TEXT_1048);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_1049);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (genClass.hasStaticFeatures()){
    stringBuffer.append(TEXT_1050);
    }
    stringBuffer.append(TEXT_1051);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1052);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_1053);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1054);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_1055);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1056);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1057);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1058);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1059);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1060);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1061);
    }
    stringBuffer.append(TEXT_1062);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1063);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_1064);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1065);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1066);
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1067);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1068);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1069);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1070);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1071);
    }
    stringBuffer.append(TEXT_1072);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1073);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1074);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_1075);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1076);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1077);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1078);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_1079);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1080);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1081);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1082);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1083);
    }
    stringBuffer.append(TEXT_1084);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1085);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1086);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1087);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1088);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1089);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1090);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1091);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1092);
    }
    stringBuffer.append(TEXT_1093);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1094);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1095);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1096);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1097);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1098);
    }
    stringBuffer.append(TEXT_1099);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1100);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1101);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_1102);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_1103);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1104);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1105);
    }
    stringBuffer.append(TEXT_1106);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1107);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1108);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1109);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1110);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1111);
    } else {
    stringBuffer.append(TEXT_1112);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1113);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1114);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1115);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1116);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1117);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1118);
    }
    }
    } else if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1119);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1120);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1121);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1122);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1123);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1124);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1125);
    }
    }
    if (!genModel.isSuppressNotification()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1126);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1127);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1128);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1129);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1130);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1131);
    } else {
    stringBuffer.append(TEXT_1132);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1133);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1134);
    }
    }
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_1135);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1136);
    if (!genModel.isVirtualDelegation()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1137);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1138);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1139);
    } else {
    stringBuffer.append(TEXT_1140);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1141);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1142);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_1143);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_1144);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1145);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1146);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1147);
    } else {
    stringBuffer.append(TEXT_1148);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_1149);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1150);
    } else {
    stringBuffer.append(TEXT_1151);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1152);
    }
    stringBuffer.append(TEXT_1153);
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1154);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1155);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1156);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1157);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1158);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1159);
    } else {
    stringBuffer.append(TEXT_1160);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1161);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1162);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1163);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1164);
    }
    } else if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1165);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1166);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1167);
    }
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1168);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1169);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1170);
    } else {
    stringBuffer.append(TEXT_1171);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1172);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1173);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_1174);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_1175);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1176);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1177);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1178);
    stringBuffer.append(genFeature.getEDefault());
    } else {
    stringBuffer.append(TEXT_1179);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_1180);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1181);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1182);
    } else {
    stringBuffer.append(TEXT_1183);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1184);
    }
    stringBuffer.append(TEXT_1185);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1186);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1187);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1188);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1189);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1190);
    } else {
    stringBuffer.append(TEXT_1191);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1192);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1193);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1194);
    }
    } else if (genClass.getUnsetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_1195);
    stringBuffer.append(genClass.getUnsetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1196);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1197);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1198);
    //Class/unsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1199);
    }
    //Class/unsetGenFeature.override.javajetinc
    }
    if (genFeature.isIsSet() && (isImplementation || !genFeature.isSuppressedIsSetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_1200);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_1201);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1202);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1203);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1204);
    stringBuffer.append(TEXT_1205);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1206);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1207);
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_1208);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1209);
    }
    stringBuffer.append(TEXT_1210);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1211);
    if (!genFeature.isListType() && genFeature.isChangeable() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_1212);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1213);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_1214);
    }
    stringBuffer.append(TEXT_1215);
    //Class/isSetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1216);
    if (isJDK50) { //Class/isSetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1217);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1218);
    } else {
    stringBuffer.append(TEXT_1219);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingIsSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_1220);
    }
    stringBuffer.append(TEXT_1221);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_1222);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (genClass.hasStaticFeatures()){
    stringBuffer.append(TEXT_1223);
    }
    stringBuffer.append(TEXT_1224);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1225);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_1226);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1227);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_1228);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1229);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1230);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1231);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1232);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1233);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1234);
    }
    stringBuffer.append(TEXT_1235);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1236);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_1237);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1238);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1239);
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1240);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1241);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1242);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1243);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1244);
    } else {
    stringBuffer.append(TEXT_1245);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1246);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1247);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1248);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1249);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1250);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1251);
    } else {
    stringBuffer.append(TEXT_1252);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1253);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1254);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1255);
    }
    } else if (genClass.getIsSetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_1256);
    stringBuffer.append(genClass.getIsSetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1257);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1258);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1259);
    //Class/isSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1260);
    }
    //Class/isSetGenFeature.override.javajetinc
    }
    //Class/genFeature.override.javajetinc
    }//for
    }}.run();
    for (GenOperation genOperation : (isImplementation ? genClass.getImplementedGenOperations() : genClass.getDeclaredGenOperations())) {
    if (isImplementation) {
    if (genOperation.isInvariant() && genOperation.hasInvariantExpression()) {
    stringBuffer.append(TEXT_1261);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1262);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1263);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_1264);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1265);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1266);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_1267);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1268);
    stringBuffer.append(genOperation.getInvariantExpression("\t\t"));
    stringBuffer.append(TEXT_1269);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_1270);
    } else if (genOperation.hasInvocationDelegate()) {
    stringBuffer.append(TEXT_1271);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1272);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1273);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_1274);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1275);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1276);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_1277);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1278);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_1279);
    stringBuffer.append(genOperation.getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_1280);
    }
    }
    if (isInterface) {
    stringBuffer.append(TEXT_1281);
    stringBuffer.append(TEXT_1282);
    if (genOperation.hasDocumentation() || genOperation.hasParameterDocumentation()) {
    stringBuffer.append(TEXT_1283);
    if (genOperation.hasDocumentation()) {
    stringBuffer.append(TEXT_1284);
    stringBuffer.append(genOperation.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    for (GenParameter genParameter : genOperation.getGenParameters()) {
    if (genParameter.hasDocumentation()) { String documentation = genParameter.getDocumentation("");
    if (documentation.contains("\n") || documentation.contains("\r")) {
    stringBuffer.append(TEXT_1285);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_1286);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1287);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_1288);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    }
    }
    stringBuffer.append(TEXT_1289);
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genOperation.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_1290);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_1291);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_1292);
    }}
    stringBuffer.append(TEXT_1293);
    //Class/genOperation.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1294);
    if (isJDK50) { //Class/genOperation.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1295);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_1296);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1297);
    stringBuffer.append(genOperation.getParameters(genClass));
    stringBuffer.append(TEXT_1298);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_1299);
    } else {
    if (genModel.useGenerics() && !genOperation.hasBody() && !genOperation.isInvariant() && genOperation.hasInvocationDelegate() && genOperation.isUncheckedCast(genClass)) {
    stringBuffer.append(TEXT_1300);
    }
    stringBuffer.append(TEXT_1301);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_1302);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1303);
    stringBuffer.append(genOperation.getParameters(isImplementation, genClass));
    stringBuffer.append(TEXT_1304);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_1305);
    if (genOperation.hasBody()) {
    stringBuffer.append(TEXT_1306);
    stringBuffer.append(genOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else if (genOperation.isInvariant()) {GenClass opClass = genOperation.getGenClass(); String diagnostics = genOperation.getGenParameters().get(0).getName(); String context = genOperation.getGenParameters().get(1).getName();
    if (genOperation.hasInvariantExpression()) {
    stringBuffer.append(TEXT_1307);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1308);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_1309);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1310);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_1311);
    stringBuffer.append(genOperation.getValidationDelegate());
    stringBuffer.append(TEXT_1312);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_1313);
    stringBuffer.append(genOperation.getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_1314);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1315);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_1316);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1317);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1318);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_1319);
    } else {
    stringBuffer.append(TEXT_1320);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1321);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1322);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_1323);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_1324);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1325);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1326);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_1327);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_1328);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1329);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
    stringBuffer.append(TEXT_1330);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_1331);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_1332);
    }
    } else if (genOperation.hasInvocationDelegate()) { int size = genOperation.getGenParameters().size();
    stringBuffer.append(TEXT_1333);
    if (genOperation.isVoid()) {
    stringBuffer.append(TEXT_1334);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1335);
    if (size > 0) {
    stringBuffer.append(TEXT_1336);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_1337);
    stringBuffer.append(size);
    stringBuffer.append(TEXT_1338);
    stringBuffer.append(genOperation.getParametersArray(genClass));
    stringBuffer.append(TEXT_1339);
    } else {
    stringBuffer.append(TEXT_1340);
    }
    stringBuffer.append(TEXT_1341);
    } else {
    stringBuffer.append(TEXT_1342);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1343);
    }
    stringBuffer.append(TEXT_1344);
    stringBuffer.append(genOperation.getObjectType(genClass));
    stringBuffer.append(TEXT_1345);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1346);
    if (size > 0) {
    stringBuffer.append(TEXT_1347);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_1348);
    stringBuffer.append(size);
    stringBuffer.append(TEXT_1349);
    stringBuffer.append(genOperation.getParametersArray(genClass));
    stringBuffer.append(TEXT_1350);
    } else {
    stringBuffer.append(TEXT_1351);
    }
    stringBuffer.append(TEXT_1352);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1353);
    stringBuffer.append(genOperation.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1354);
    }
    stringBuffer.append(TEXT_1355);
    }
    stringBuffer.append(TEXT_1356);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_1357);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.WrappedException"));
    stringBuffer.append(TEXT_1358);
    } else {
    stringBuffer.append(TEXT_1359);
    //Class/implementedGenOperation.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1360);
    }
    //Class/implementedGenOperation.override.javajetinc
    }//for
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseAddGenFeatures())) {
    stringBuffer.append(TEXT_1361);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass)) {
    stringBuffer.append(TEXT_1362);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1363);
    }
    stringBuffer.append(TEXT_1364);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1365);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1366);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1367);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1368);
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    stringBuffer.append(TEXT_1369);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1370);
    if (genFeature.isListType()) { String cast = "("  + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + (!genModel.useGenerics() ? ")" : "<" + genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject") + ">)(" + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + "<?>)");
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1371);
    stringBuffer.append(cast);
    stringBuffer.append(TEXT_1372);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1373);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1374);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1375);
    } else {
    stringBuffer.append(TEXT_1376);
    stringBuffer.append(cast);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1377);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_1378);
    if (genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1379);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1380);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1381);
    } else {
    stringBuffer.append(TEXT_1382);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1383);
    }
    } else {
    if (genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1384);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1385);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1386);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1387);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1388);
    } else if (genFeature.isVolatile() || genClass.getImplementingGenModel(genFeature).isDynamicDelegation()) {
    stringBuffer.append(TEXT_1389);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1390);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1391);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_1392);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_1393);
    }
    stringBuffer.append(TEXT_1394);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1395);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_1396);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1397);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1398);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1399);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_1400);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1401);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1402);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1403);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1404);
    }
    stringBuffer.append(TEXT_1405);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1406);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1407);
    }
    }
    stringBuffer.append(TEXT_1408);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1409);
    } else {
    stringBuffer.append(TEXT_1410);
    }
    stringBuffer.append(TEXT_1411);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseRemoveGenFeatures())) {
    stringBuffer.append(TEXT_1412);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1413);
    }
    stringBuffer.append(TEXT_1414);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1415);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1416);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1417);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1418);
    for (GenFeature genFeature : genClass.getEInverseRemoveGenFeatures()) {
    stringBuffer.append(TEXT_1419);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1420);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1421);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1422);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1423);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1424);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1425);
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1426);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1427);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1428);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1429);
    } else {
    stringBuffer.append(TEXT_1430);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1431);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1432);
    }
    } else if (genFeature.isContainer() && !genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1433);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1434);
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1435);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1436);
    } else {
    stringBuffer.append(TEXT_1437);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1438);
    }
    }
    stringBuffer.append(TEXT_1439);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1440);
    } else {
    stringBuffer.append(TEXT_1441);
    }
    stringBuffer.append(TEXT_1442);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEBasicRemoveFromContainerGenFeatures())) {
    stringBuffer.append(TEXT_1443);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1444);
    }
    stringBuffer.append(TEXT_1445);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1446);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1447);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1448);
    for (GenFeature genFeature : genClass.getEBasicRemoveFromContainerGenFeatures()) {
    GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_1449);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1450);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1451);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1452);
    }
    stringBuffer.append(TEXT_1453);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1454);
    } else {
    stringBuffer.append(TEXT_1455);
    }
    stringBuffer.append(TEXT_1456);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEGetGenFeatures())) {
    stringBuffer.append(TEXT_1457);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1458);
    }
    stringBuffer.append(TEXT_1459);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1460);
    for (GenFeature genFeature : genClass.getEGetGenFeatures()) {
    stringBuffer.append(TEXT_1461);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1462);
    if (genFeature.isPrimitiveType()) {
    if (isJDK50) {
    stringBuffer.append(TEXT_1463);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1464);
    } else if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1465);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1466);
    } else {
    stringBuffer.append(TEXT_1467);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1468);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1469);
    }
    } else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_1470);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1471);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1472);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1473);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1474);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1475);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1476);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1477);
    } else {
    stringBuffer.append(TEXT_1478);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1479);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1480);
    }
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1481);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1482);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1483);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1484);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1485);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1486);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1487);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1488);
    } else {
    stringBuffer.append(TEXT_1489);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1490);
    }
    }
    stringBuffer.append(TEXT_1491);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1492);
    } else {
    stringBuffer.append(TEXT_1493);
    }
    stringBuffer.append(TEXT_1494);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getESetGenFeatures())) {
    stringBuffer.append(TEXT_1495);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass) && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_1496);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1497);
    }
    stringBuffer.append(TEXT_1498);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1499);
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    stringBuffer.append(TEXT_1500);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1501);
    if (genFeature.isListType()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1502);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1503);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1504);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1505);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1506);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1507);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1508);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1509);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1510);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1511);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1512);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1513);
    } else {
    stringBuffer.append(TEXT_1514);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1515);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1516);
    }
    } else {
    stringBuffer.append(TEXT_1517);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1518);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1519);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    if (isJDK50) {
    stringBuffer.append(TEXT_1520);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_1521);
    }
    stringBuffer.append(TEXT_1522);
    }
    } else if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1523);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1524);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1525);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1526);
    } else {
    stringBuffer.append(TEXT_1527);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1528);
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType() || !genFeature.getRawType().equals(genFeature.getType(genClass))) {
    stringBuffer.append(TEXT_1529);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1530);
    }
    stringBuffer.append(TEXT_1531);
    }
    stringBuffer.append(TEXT_1532);
    }
    stringBuffer.append(TEXT_1533);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1534);
    } else {
    stringBuffer.append(TEXT_1535);
    }
    stringBuffer.append(TEXT_1536);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEUnsetGenFeatures())) {
    stringBuffer.append(TEXT_1537);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1538);
    }
    stringBuffer.append(TEXT_1539);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1540);
    for (GenFeature genFeature : genClass.getEUnsetGenFeatures()) {
    stringBuffer.append(TEXT_1541);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1542);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1543);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1544);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1545);
    } else {
    stringBuffer.append(TEXT_1546);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1547);
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1548);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1549);
    } else if (!genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_1550);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1551);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1552);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_1553);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1554);
    } else {
    stringBuffer.append(TEXT_1555);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1556);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1557);
    }
    stringBuffer.append(TEXT_1558);
    }
    stringBuffer.append(TEXT_1559);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1560);
    } else {
    stringBuffer.append(TEXT_1561);
    }
    stringBuffer.append(TEXT_1562);
    //Class/eUnset.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEIsSetGenFeatures())) {
    stringBuffer.append(TEXT_1563);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) {
    if (genFeature.isListType() && !genFeature.isUnsettable() && !genFeature.isWrappedFeatureMapType() && !genClass.isField(genFeature) && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1564);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1565);
    }
    stringBuffer.append(TEXT_1566);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1567);
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) { String safeNameAccessor = genFeature.getSafeName(); if ("featureID".equals(safeNameAccessor)) { safeNameAccessor = "this." + safeNameAccessor; }
    stringBuffer.append(TEXT_1568);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1569);
    if (genFeature.hasSettingDelegate()) {
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1570);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1571);
    } else {
    stringBuffer.append(TEXT_1572);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1573);
    }
    } else if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_1574);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1575);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1576);
    } else {
    stringBuffer.append(TEXT_1577);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1578);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1579);
    }
    } else {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1580);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1581);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1582);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1583);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1584);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1585);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1586);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1587);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1588);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1589);
    } else {
    stringBuffer.append(TEXT_1590);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1591);
    }
    }
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1592);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1593);
    } else if (genFeature.isResolveProxies()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1594);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1595);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1596);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1597);
    } else {
    stringBuffer.append(TEXT_1598);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1599);
    }
    }
    } else if (!genFeature.hasEDefault()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1600);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1601);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1602);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1603);
    } else {
    stringBuffer.append(TEXT_1604);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1605);
    }
    }
    } else if (genFeature.isPrimitiveType() || genFeature.isEnumType()) {
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1606);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1607);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1608);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1609);
    } else {
    stringBuffer.append(TEXT_1610);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1611);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1612);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1613);
    }
    } else {
    stringBuffer.append(TEXT_1614);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1615);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1616);
    }
    } else {
    if (genFeature.isEnumType() && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1617);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1618);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1619);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1620);
    } else {
    stringBuffer.append(TEXT_1621);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1622);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1623);
    }
    }
    } else {//datatype
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1624);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1625);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1626);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1627);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1628);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1629);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1630);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1631);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1632);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1633);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1634);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1635);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1636);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1637);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1638);
    } else {
    stringBuffer.append(TEXT_1639);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1640);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1641);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1642);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1643);
    }
    }
    }
    }
    stringBuffer.append(TEXT_1644);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1645);
    } else {
    stringBuffer.append(TEXT_1646);
    }
    stringBuffer.append(TEXT_1647);
    //Class/eIsSet.override.javajetinc
    }
    if (isImplementation && (!genClass.getMixinGenFeatures().isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty())) {
    if (!genClass.getMixinGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1648);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1649);
    }
    stringBuffer.append(TEXT_1650);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1651);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1652);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1653);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1654);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1655);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1656);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1657);
    }
    stringBuffer.append(TEXT_1658);
    }
    stringBuffer.append(TEXT_1659);
    }
    stringBuffer.append(TEXT_1660);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1661);
    }
    stringBuffer.append(TEXT_1662);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1663);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1664);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1665);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1666);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1667);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1668);
    }
    stringBuffer.append(TEXT_1669);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1670);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1671);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1672);
    for (GenFeature genFeature : genClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1673);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1674);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1675);
    }
    stringBuffer.append(TEXT_1676);
    }
    stringBuffer.append(TEXT_1677);
    }
    if (genModel.isOperationReflection() && isImplementation && (!genClass.getMixinGenOperations().isEmpty() || !genClass.getOverrideGenOperations(genClass.getExtendedGenOperations(), genClass.getImplementedGenOperations()).isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty())) {
    stringBuffer.append(TEXT_1678);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1679);
    }
    stringBuffer.append(TEXT_1680);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1681);
    for (GenClass extendedGenClass : genClass.getExtendedGenClasses()) { List<GenOperation> extendedImplementedGenOperations = extendedGenClass.getImplementedGenOperations(); List<GenOperation> implementedGenOperations = genClass.getImplementedGenOperations();
    if (!genClass.getOverrideGenOperations(extendedImplementedGenOperations, implementedGenOperations).isEmpty()) {
    stringBuffer.append(TEXT_1682);
    stringBuffer.append(extendedGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1683);
    for (GenOperation genOperation : extendedImplementedGenOperations) { GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    if (implementedGenOperations.contains(overrideGenOperation)) {
    stringBuffer.append(TEXT_1684);
    stringBuffer.append(extendedGenClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1685);
    stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1686);
    }
    }
    stringBuffer.append(TEXT_1687);
    }
    }
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1688);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1689);
    for (GenOperation genOperation : mixinGenClass.getGenOperations()) { GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    stringBuffer.append(TEXT_1690);
    stringBuffer.append(mixinGenClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1691);
    stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation != null ? overrideGenOperation : genOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1692);
    }
    stringBuffer.append(TEXT_1693);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_1694);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1695);
    stringBuffer.append(negativeOperationOffsetCorrection);
    stringBuffer.append(TEXT_1696);
    for (GenOperation genOperation : genClass.getGenOperations()) {
    stringBuffer.append(TEXT_1697);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1698);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1699);
    }
    stringBuffer.append(TEXT_1700);
    }
    stringBuffer.append(TEXT_1701);
    }
    if (isImplementation && genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_1702);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1703);
    }
    stringBuffer.append(TEXT_1704);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1705);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1706);
    }
    stringBuffer.append(TEXT_1707);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1708);
    }
    { List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
    if (!eVirtualIndexBitFields.isEmpty()) { List<String> allEVirtualIndexBitFields = genClass.getAllEVirtualIndexBitFields(new ArrayList<String>());
    stringBuffer.append(TEXT_1709);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1710);
    }
    stringBuffer.append(TEXT_1711);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1712);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1713);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1714);
    }
    stringBuffer.append(TEXT_1715);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1716);
    }
    stringBuffer.append(TEXT_1717);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1718);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1719);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1720);
    }
    stringBuffer.append(TEXT_1721);
    }
    }
    }
    if (genModel.isOperationReflection() && isImplementation && !genClass.getImplementedGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_1722);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1723);
    }
    if (genModel.useGenerics()) {
    boolean isUnchecked = false; boolean isRaw = false; LOOP: for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenOperations() : genClass.getAllGenOperations())) { for (GenParameter genParameter : genOperation.getGenParameters()) { if (genParameter.isUncheckedCast()) { if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType()) { isUnchecked = true; } if (genParameter.usesOperationTypeParameters() && !genParameter.getEcoreParameter().getEGenericType().getETypeArguments().isEmpty()) { isRaw = true; break LOOP; }}}}
    if (isUnchecked) {
    stringBuffer.append(TEXT_1724);
    if (!isRaw) {
    stringBuffer.append(TEXT_1725);
    } else {
    stringBuffer.append(TEXT_1726);
    }
    stringBuffer.append(TEXT_1727);
    }
    }
    stringBuffer.append(TEXT_1728);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1729);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_1730);
    stringBuffer.append(negativeOperationOffsetCorrection);
    stringBuffer.append(TEXT_1731);
    for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenOperations() : genClass.getAllGenOperations())) { List<GenParameter> genParameters = genOperation.getGenParameters(); int size = genParameters.size();  boolean hasCheckedException = genOperation.hasCheckedException(); String indent = hasCheckedException ? "\t" : ""; GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    stringBuffer.append(TEXT_1732);
    stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation != null ? overrideGenOperation : genOperation));
    stringBuffer.append(TEXT_1733);
    if (hasCheckedException) {
    stringBuffer.append(TEXT_1734);
    /*}*/}
    if (genOperation.isVoid()) {
    stringBuffer.append(TEXT_1735);
    stringBuffer.append(indent);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1736);
    for (int i = 0; i < size; i++) { GenParameter genParameter = genParameters.get(i);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1737);
    }
    if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType() || !genParameter.usesOperationTypeParameters() && !genParameter.getRawType().equals(genParameter.getType(genClass))) {
    stringBuffer.append(TEXT_1738);
    stringBuffer.append(genParameter.usesOperationTypeParameters() ? genParameter.getRawImportedType() : genParameter.getObjectType(genClass));
    stringBuffer.append(TEXT_1739);
    }
    stringBuffer.append(TEXT_1740);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1741);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1742);
    stringBuffer.append(genParameter.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1743);
    }
    if (i < (size - 1)) {
    stringBuffer.append(TEXT_1744);
    }
    }
    stringBuffer.append(TEXT_1745);
    stringBuffer.append(indent);
    stringBuffer.append(TEXT_1746);
    } else {
    stringBuffer.append(TEXT_1747);
    stringBuffer.append(indent);
    stringBuffer.append(TEXT_1748);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1749);
    stringBuffer.append(genOperation.getObjectType(genClass));
    stringBuffer.append(TEXT_1750);
    }
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1751);
    for (int i = 0; i < size; i++) { GenParameter genParameter = genParameters.get(i);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1752);
    }
    if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType() || !genParameter.usesOperationTypeParameters() && !genParameter.getRawType().equals(genParameter.getType(genClass))) {
    stringBuffer.append(TEXT_1753);
    stringBuffer.append(genParameter.usesOperationTypeParameters() ? genParameter.getRawImportedType() : genParameter.getObjectType(genClass));
    stringBuffer.append(TEXT_1754);
    }
    stringBuffer.append(TEXT_1755);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1756);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1757);
    stringBuffer.append(genParameter.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1758);
    }
    if (i < (size - 1)) {
    stringBuffer.append(TEXT_1759);
    }
    }
    stringBuffer.append(TEXT_1760);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1761);
    }
    stringBuffer.append(TEXT_1762);
    }
    if (hasCheckedException) {/*{*/
    stringBuffer.append(TEXT_1763);
    stringBuffer.append(genModel.getImportedName("java.lang.Throwable"));
    stringBuffer.append(TEXT_1764);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_1765);
    }
    }
    stringBuffer.append(TEXT_1766);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1767);
    } else {
    stringBuffer.append(TEXT_1768);
    }
    stringBuffer.append(TEXT_1769);
    }
    if (!genClass.hasImplementedToStringGenOperation() && isImplementation && !genModel.isReflectiveDelegation() && !genModel.isDynamicDelegation() && !genClass.getToStringGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1770);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1771);
    }
    stringBuffer.append(TEXT_1772);
    { boolean first = true;
    for (GenFeature genFeature : genClass.getToStringGenFeatures()) {
    if (first) { first = false;
    stringBuffer.append(TEXT_1773);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1774);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1775);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1776);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.isUnsettable() && !genFeature.isListType()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1777);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1778);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1779);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1780);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1781);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1782);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1783);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1784);
    }
    stringBuffer.append(TEXT_1785);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1786);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1787);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1788);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1789);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1790);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1791);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1792);
    }
    stringBuffer.append(TEXT_1793);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1794);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1795);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1796);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1797);
    stringBuffer.append(genModel.getNonNLS());
    }
    } else {
    stringBuffer.append(TEXT_1798);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1799);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1800);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1801);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1802);
    }
    stringBuffer.append(TEXT_1803);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1804);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1805);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (!genFeature.isListType() && !genFeature.isReferenceType()){
    stringBuffer.append(TEXT_1806);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_1807);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1808);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1809);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1810);
    } else {
    stringBuffer.append(TEXT_1811);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1812);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1813);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1814);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1815);
    }
    } else {
    stringBuffer.append(TEXT_1816);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1817);
    }
    }
    }
    }
    }
    stringBuffer.append(TEXT_1818);
    }
    if (isImplementation && genClass.isMapEntry()) { GenFeature keyFeature = genClass.getMapEntryKeyFeature(); GenFeature valueFeature = genClass.getMapEntryValueFeature();
    String objectType = genModel.getImportedName("java.lang.Object");
    String keyType = isJDK50 ? keyFeature.getObjectType(genClass) : objectType;
    String valueType = isJDK50 ? valueFeature.getObjectType(genClass) : objectType;
    String eMapType = genModel.getImportedName("org.eclipse.emf.common.util.EMap") + (isJDK50 ? "<" + keyType + ", " + valueType + ">" : "");
    stringBuffer.append(TEXT_1819);
    if (isGWT) {
    stringBuffer.append(TEXT_1820);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1821);
    stringBuffer.append(objectType);
    stringBuffer.append(TEXT_1822);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1823);
    if (!isJDK50 && keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1824);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1825);
    } else {
    stringBuffer.append(TEXT_1826);
    }
    stringBuffer.append(TEXT_1827);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1828);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_1829);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1830);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1831);
    }
    stringBuffer.append(TEXT_1832);
    } else if (isJDK50) {
    stringBuffer.append(TEXT_1833);
    } else if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1834);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1835);
    stringBuffer.append(keyFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1836);
    } else {
    stringBuffer.append(TEXT_1837);
    stringBuffer.append(keyFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1838);
    }
    stringBuffer.append(TEXT_1839);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1840);
    if (!isJDK50 && valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1841);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1842);
    } else {
    stringBuffer.append(TEXT_1843);
    }
    stringBuffer.append(TEXT_1844);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1845);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1846);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1847);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_1848);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1849);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1850);
    }
    stringBuffer.append(TEXT_1851);
    } else if (isJDK50) {
    stringBuffer.append(TEXT_1852);
    } else if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1853);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1854);
    stringBuffer.append(valueFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1855);
    } else {
    stringBuffer.append(TEXT_1856);
    stringBuffer.append(valueFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1857);
    }
    stringBuffer.append(TEXT_1858);
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_1859);
    }
    stringBuffer.append(TEXT_1860);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1861);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_1862);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1863);
    }
    stringBuffer.append(TEXT_1864);
    stringBuffer.append(isInterface ? " " + genClass.getInterfaceName() : genClass.getClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_1865);
    return stringBuffer.toString();
  }
}
