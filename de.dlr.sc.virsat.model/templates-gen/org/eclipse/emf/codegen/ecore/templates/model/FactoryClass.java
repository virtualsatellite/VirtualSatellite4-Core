package org.eclipse.emf.codegen.ecore.templates.model;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class FactoryClass
{
  protected static String nl;
  public static synchronized FactoryClass create(String lineSeparator)
  {
    nl = lineSeparator;
    FactoryClass result = new FactoryClass();
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
  protected final String TEXT_11 = NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * The <b>Factory</b> for the model." + NL + " * It provides a create method for each non-abstract class of the model." + NL + " * <!-- end-user-doc -->";
  protected final String TEXT_12 = NL + " * @see ";
  protected final String TEXT_13 = NL + " * @generated" + NL + " */";
  protected final String TEXT_14 = NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * An implementation of the model <b>Factory</b>." + NL + " * <!-- end-user-doc -->" + NL + " * @generated" + NL + " */";
  protected final String TEXT_15 = NL + "public class ";
  protected final String TEXT_16 = " extends ";
  protected final String TEXT_17 = " implements ";
  protected final String TEXT_18 = NL + "public interface ";
  protected final String TEXT_19 = " extends ";
  protected final String TEXT_20 = NL + "{";
  protected final String TEXT_21 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_22 = " copyright = ";
  protected final String TEXT_23 = ";";
  protected final String TEXT_24 = NL;
  protected final String TEXT_25 = NL + "\t/**" + NL + "\t * The singleton instance of the factory." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_26 = " eINSTANCE = init();" + NL;
  protected final String TEXT_27 = NL + "\t/**" + NL + "\t * The singleton instance of the factory." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_28 = " INSTANCE = ";
  protected final String TEXT_29 = ".eINSTANCE;" + NL;
  protected final String TEXT_30 = NL + "\t/**" + NL + "\t * The singleton instance of the factory." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_31 = " eINSTANCE = ";
  protected final String TEXT_32 = ".init();" + NL;
  protected final String TEXT_33 = NL + "\t/**" + NL + "\t * Creates the default factory implementation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_34 = NL + "\tpublic static ";
  protected final String TEXT_35 = " init()" + NL + "\t{" + NL + "\t\ttry" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_36 = " the";
  protected final String TEXT_37 = " = (";
  protected final String TEXT_38 = ")";
  protected final String TEXT_39 = ".Registry.INSTANCE.getEFactory(";
  protected final String TEXT_40 = ".eNS_URI);" + NL + "\t\t\tif (the";
  protected final String TEXT_41 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\treturn the";
  protected final String TEXT_42 = ";" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\tcatch (Exception exception)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_43 = ".INSTANCE.log(exception);" + NL + "\t\t}" + NL + "\t\treturn new ";
  protected final String TEXT_44 = "();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Creates an instance of the factory." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_45 = "()" + NL + "\t{" + NL + "\t\tsuper();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_46 = NL + "\t@Override";
  protected final String TEXT_47 = NL + "\tpublic EObject create(EClass eClass)" + NL + "\t{" + NL + "\t\tswitch (eClass.getClassifierID())" + NL + "\t\t{";
  protected final String TEXT_48 = NL + "\t\t\tcase ";
  protected final String TEXT_49 = ".";
  protected final String TEXT_50 = ": return ";
  protected final String TEXT_51 = "create";
  protected final String TEXT_52 = "();";
  protected final String TEXT_53 = NL + "\t\t\tdefault:" + NL + "\t\t\t\tthrow new IllegalArgumentException(\"The class '\" + eClass.getName() + \"' is not a valid classifier\");";
  protected final String TEXT_54 = NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_55 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_56 = NL + "\t@Override";
  protected final String TEXT_57 = NL + "\tpublic Object createFromString(";
  protected final String TEXT_58 = " eDataType, String initialValue)" + NL + "\t{" + NL + "\t\tswitch (eDataType.getClassifierID())" + NL + "\t\t{";
  protected final String TEXT_59 = NL + "\t\t\tcase ";
  protected final String TEXT_60 = ".";
  protected final String TEXT_61 = ":" + NL + "\t\t\t\treturn create";
  protected final String TEXT_62 = "FromString(eDataType, initialValue);";
  protected final String TEXT_63 = NL + "\t\t\tdefault:" + NL + "\t\t\t\tthrow new IllegalArgumentException(\"The datatype '\" + eDataType.getName() + \"' is not a valid classifier\");";
  protected final String TEXT_64 = NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_65 = NL + "\t@Override";
  protected final String TEXT_66 = NL + "\tpublic String convertToString(";
  protected final String TEXT_67 = " eDataType, Object instanceValue)" + NL + "\t{" + NL + "\t\tswitch (eDataType.getClassifierID())" + NL + "\t\t{";
  protected final String TEXT_68 = NL + "\t\t\tcase ";
  protected final String TEXT_69 = ".";
  protected final String TEXT_70 = ":" + NL + "\t\t\t\treturn convert";
  protected final String TEXT_71 = "ToString(eDataType, instanceValue);";
  protected final String TEXT_72 = NL + "\t\t\tdefault:" + NL + "\t\t\t\tthrow new IllegalArgumentException(\"The datatype '\" + eDataType.getName() + \"' is not a valid classifier\");";
  protected final String TEXT_73 = NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_74 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_75 = " create";
  protected final String TEXT_76 = "()" + NL + "\t{";
  protected final String TEXT_77 = NL + "\t\t";
  protected final String TEXT_78 = " ";
  protected final String TEXT_79 = " = ";
  protected final String TEXT_80 = "super.create(";
  protected final String TEXT_81 = ");";
  protected final String TEXT_82 = NL + "\t\t";
  protected final String TEXT_83 = " ";
  protected final String TEXT_84 = " = new ";
  protected final String TEXT_85 = "()";
  protected final String TEXT_86 = "{}";
  protected final String TEXT_87 = ";";
  protected final String TEXT_88 = NL + "\t\treturn ";
  protected final String TEXT_89 = ";" + NL + "\t}" + NL;
  protected final String TEXT_90 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_91 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_92 = NL + "\tpublic ";
  protected final String TEXT_93 = " create";
  protected final String TEXT_94 = "(";
  protected final String TEXT_95 = "final ";
  protected final String TEXT_96 = "String ";
  protected final String TEXT_97 = "it";
  protected final String TEXT_98 = "literal";
  protected final String TEXT_99 = ")" + NL + "\t{";
  protected final String TEXT_100 = NL + "\t\t";
  protected final String TEXT_101 = NL + "\t\t";
  protected final String TEXT_102 = " result = ";
  protected final String TEXT_103 = ".get(literal);" + NL + "\t\tif (result == null) throw new IllegalArgumentException(\"The value '\" + literal + \"' is not a valid enumerator of '\" + ";
  protected final String TEXT_104 = ".getName() + \"'\");";
  protected final String TEXT_105 = NL + "\t\treturn result;";
  protected final String TEXT_106 = NL + "\t\treturn new ";
  protected final String TEXT_107 = "(create";
  protected final String TEXT_108 = "(literal));";
  protected final String TEXT_109 = NL + "\t\treturn create";
  protected final String TEXT_110 = "(literal);";
  protected final String TEXT_111 = NL + "\t\treturn new ";
  protected final String TEXT_112 = "(";
  protected final String TEXT_113 = ".create";
  protected final String TEXT_114 = "(literal));";
  protected final String TEXT_115 = NL + "\t\treturn ";
  protected final String TEXT_116 = ".create";
  protected final String TEXT_117 = "(literal);";
  protected final String TEXT_118 = NL + "\t\treturn ";
  protected final String TEXT_119 = "(";
  protected final String TEXT_120 = ")";
  protected final String TEXT_121 = ".createFromString(";
  protected final String TEXT_122 = ", literal);";
  protected final String TEXT_123 = NL + "\t\tif (literal == null) return null;" + NL + "\t\t";
  protected final String TEXT_124 = " result = new ";
  protected final String TEXT_125 = "<";
  protected final String TEXT_126 = ">";
  protected final String TEXT_127 = "();";
  protected final String TEXT_128 = NL + "\t\tfor (";
  protected final String TEXT_129 = " stringTokenizer = new ";
  protected final String TEXT_130 = "(literal); stringTokenizer.hasMoreTokens(); )";
  protected final String TEXT_131 = NL + "\t\tfor (String item : split(literal))";
  protected final String TEXT_132 = NL + "\t\t{";
  protected final String TEXT_133 = NL + "\t\t\tString item = stringTokenizer.nextToken();";
  protected final String TEXT_134 = NL + "\t\t\tresult.add(create";
  protected final String TEXT_135 = "(item));";
  protected final String TEXT_136 = NL + "\t\t\tresult.add(create";
  protected final String TEXT_137 = "FromString(";
  protected final String TEXT_138 = ", item));";
  protected final String TEXT_139 = NL + "\t\t\tresult.add(";
  protected final String TEXT_140 = ".create";
  protected final String TEXT_141 = "(item));";
  protected final String TEXT_142 = NL + "\t\t\tresult.add(";
  protected final String TEXT_143 = ".createFromString(";
  protected final String TEXT_144 = ", item));";
  protected final String TEXT_145 = NL + "\t\t}" + NL + "\t\treturn result;";
  protected final String TEXT_146 = NL + "\t\tif (literal == null) return ";
  protected final String TEXT_147 = ";" + NL + "\t\t";
  protected final String TEXT_148 = " result = ";
  protected final String TEXT_149 = ";" + NL + "\t\tRuntimeException exception = null;";
  protected final String TEXT_150 = NL + "\t\ttry" + NL + "\t\t{";
  protected final String TEXT_151 = NL + "\t\t\tresult = create";
  protected final String TEXT_152 = "(literal);";
  protected final String TEXT_153 = NL + "\t\t\tresult = (";
  protected final String TEXT_154 = ")create";
  protected final String TEXT_155 = "FromString(";
  protected final String TEXT_156 = ", literal);";
  protected final String TEXT_157 = NL + "\t\t\tresult = ";
  protected final String TEXT_158 = ".create";
  protected final String TEXT_159 = "(literal);";
  protected final String TEXT_160 = NL + "\t\t\tresult = (";
  protected final String TEXT_161 = ")";
  protected final String TEXT_162 = ".createFromString(";
  protected final String TEXT_163 = ", literal);";
  protected final String TEXT_164 = NL + "\t\t\tif (";
  protected final String TEXT_165 = "result != null && ";
  protected final String TEXT_166 = ".INSTANCE.validate(";
  protected final String TEXT_167 = ", ";
  protected final String TEXT_168 = "new ";
  protected final String TEXT_169 = "(result)";
  protected final String TEXT_170 = "result";
  protected final String TEXT_171 = ", null, null))" + NL + "\t\t\t{" + NL + "\t\t\t\treturn result;" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\tcatch (RuntimeException e)" + NL + "\t\t{" + NL + "\t\t\texception = e;" + NL + "\t\t}";
  protected final String TEXT_172 = NL + "\t\tif (";
  protected final String TEXT_173 = "result != null || ";
  protected final String TEXT_174 = "exception == null) return result;" + NL + "    " + NL + "\t\tthrow exception;";
  protected final String TEXT_175 = NL + "\t\treturn (";
  protected final String TEXT_176 = ")super.createFromString(literal);";
  protected final String TEXT_177 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new ";
  protected final String TEXT_178 = "();";
  protected final String TEXT_179 = NL + "\t\treturn ((";
  protected final String TEXT_180 = ")super.createFromString(";
  protected final String TEXT_181 = ", literal)).";
  protected final String TEXT_182 = "();";
  protected final String TEXT_183 = NL + "\t\treturn ";
  protected final String TEXT_184 = "(";
  protected final String TEXT_185 = ")";
  protected final String TEXT_186 = "super.createFromString(";
  protected final String TEXT_187 = ", literal);";
  protected final String TEXT_188 = NL + "\t}" + NL;
  protected final String TEXT_189 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_190 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_191 = NL + "\tpublic ";
  protected final String TEXT_192 = " create";
  protected final String TEXT_193 = "FromString(";
  protected final String TEXT_194 = " eDataType, String initialValue)" + NL + "\t{";
  protected final String TEXT_195 = NL + "\t\treturn create";
  protected final String TEXT_196 = "(initialValue);";
  protected final String TEXT_197 = NL + "\t\t";
  protected final String TEXT_198 = " result = ";
  protected final String TEXT_199 = ".get(initialValue);" + NL + "\t\tif (result == null) throw new IllegalArgumentException(\"The value '\" + initialValue + \"' is not a valid enumerator of '\" + eDataType.getName() + \"'\");";
  protected final String TEXT_200 = NL + "\t\treturn result;";
  protected final String TEXT_201 = NL + "\t\treturn ";
  protected final String TEXT_202 = "(";
  protected final String TEXT_203 = ")";
  protected final String TEXT_204 = "create";
  protected final String TEXT_205 = "FromString(";
  protected final String TEXT_206 = ", initialValue);";
  protected final String TEXT_207 = NL + "\t\treturn ";
  protected final String TEXT_208 = "(";
  protected final String TEXT_209 = ")";
  protected final String TEXT_210 = ".createFromString(";
  protected final String TEXT_211 = ", initialValue);";
  protected final String TEXT_212 = NL + "\t\treturn create";
  protected final String TEXT_213 = "(initialValue);";
  protected final String TEXT_214 = NL + "\t\tif (initialValue == null) return null;" + NL + "\t\t";
  protected final String TEXT_215 = " result = new ";
  protected final String TEXT_216 = "<";
  protected final String TEXT_217 = ">";
  protected final String TEXT_218 = "();";
  protected final String TEXT_219 = NL + "\t\tfor (";
  protected final String TEXT_220 = " stringTokenizer = new ";
  protected final String TEXT_221 = "(initialValue); stringTokenizer.hasMoreTokens(); )";
  protected final String TEXT_222 = NL + "\t\tfor (String item : split(initialValue))";
  protected final String TEXT_223 = NL + "\t\t{";
  protected final String TEXT_224 = NL + "\t\t\tString item = stringTokenizer.nextToken();";
  protected final String TEXT_225 = NL + "\t\t\tresult.add(create";
  protected final String TEXT_226 = "FromString(";
  protected final String TEXT_227 = ", item));";
  protected final String TEXT_228 = NL + "\t\t\tresult.add(";
  protected final String TEXT_229 = "(";
  protected final String TEXT_230 = ")";
  protected final String TEXT_231 = ".createFromString(";
  protected final String TEXT_232 = ", item));";
  protected final String TEXT_233 = NL + "\t\t}" + NL + "\t\treturn result;";
  protected final String TEXT_234 = NL + "\t\treturn new ";
  protected final String TEXT_235 = "(create";
  protected final String TEXT_236 = "(initialValue));";
  protected final String TEXT_237 = NL + "\t\treturn create";
  protected final String TEXT_238 = "(initialValue);";
  protected final String TEXT_239 = NL + "\t\tif (initialValue == null) return null;" + NL + "\t\t";
  protected final String TEXT_240 = " result = null;" + NL + "\t\tRuntimeException exception = null;";
  protected final String TEXT_241 = NL + "\t\ttry" + NL + "\t\t{";
  protected final String TEXT_242 = NL + "\t\t\tresult = ";
  protected final String TEXT_243 = "(";
  protected final String TEXT_244 = ")";
  protected final String TEXT_245 = "create";
  protected final String TEXT_246 = "FromString(";
  protected final String TEXT_247 = ", initialValue);";
  protected final String TEXT_248 = NL + "\t\t\tresult = ";
  protected final String TEXT_249 = "(";
  protected final String TEXT_250 = ")";
  protected final String TEXT_251 = ".createFromString(";
  protected final String TEXT_252 = ", initialValue);";
  protected final String TEXT_253 = NL + "\t\t\tif (result != null && ";
  protected final String TEXT_254 = ".INSTANCE.validate(eDataType, result, null, null))" + NL + "\t\t\t{" + NL + "\t\t\t\treturn result;" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\tcatch (RuntimeException e)" + NL + "\t\t{" + NL + "\t\t\texception = e;" + NL + "\t\t}";
  protected final String TEXT_255 = NL + "\t\tif (result != null || exception == null) return result;" + NL + "    " + NL + "\t\tthrow exception;";
  protected final String TEXT_256 = NL + "\t\treturn create";
  protected final String TEXT_257 = "(initialValue);";
  protected final String TEXT_258 = NL + "\t\treturn ";
  protected final String TEXT_259 = "(";
  protected final String TEXT_260 = ")";
  protected final String TEXT_261 = "super.createFromString(initialValue);";
  protected final String TEXT_262 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new ";
  protected final String TEXT_263 = "();";
  protected final String TEXT_264 = NL + "\t\treturn ";
  protected final String TEXT_265 = "(";
  protected final String TEXT_266 = ")";
  protected final String TEXT_267 = "super.createFromString(eDataType, initialValue);";
  protected final String TEXT_268 = NL + "\t}" + NL;
  protected final String TEXT_269 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic String convert";
  protected final String TEXT_270 = "(";
  protected final String TEXT_271 = "final ";
  protected final String TEXT_272 = " ";
  protected final String TEXT_273 = "it";
  protected final String TEXT_274 = "instanceValue";
  protected final String TEXT_275 = ")" + NL + "\t{";
  protected final String TEXT_276 = NL + "\t\t";
  protected final String TEXT_277 = NL + "\t\treturn instanceValue == null ? null : instanceValue.toString();";
  protected final String TEXT_278 = NL + "\t\treturn instanceValue == null ? null : convert";
  protected final String TEXT_279 = "(instanceValue";
  protected final String TEXT_280 = ".";
  protected final String TEXT_281 = "()";
  protected final String TEXT_282 = ");";
  protected final String TEXT_283 = NL + "\t\treturn convert";
  protected final String TEXT_284 = "(instanceValue);";
  protected final String TEXT_285 = NL + "\t\treturn ";
  protected final String TEXT_286 = ".convert";
  protected final String TEXT_287 = "(instanceValue);";
  protected final String TEXT_288 = NL + "\t\treturn ";
  protected final String TEXT_289 = ".convertToString(";
  protected final String TEXT_290 = ", instanceValue);";
  protected final String TEXT_291 = NL + "\t\tif (instanceValue == null) return null;" + NL + "\t\tif (instanceValue.isEmpty()) return \"\";" + NL + "\t\t";
  protected final String TEXT_292 = " result = new ";
  protected final String TEXT_293 = "();";
  protected final String TEXT_294 = NL + "\t\tfor (";
  protected final String TEXT_295 = " i = instanceValue.iterator(); i.hasNext(); )";
  protected final String TEXT_296 = NL + "\t\tfor (";
  protected final String TEXT_297 = " item : instanceValue)";
  protected final String TEXT_298 = NL + "\t\t{";
  protected final String TEXT_299 = NL + "\t\t\tresult.append(convert";
  protected final String TEXT_300 = "((";
  protected final String TEXT_301 = ")";
  protected final String TEXT_302 = "));";
  protected final String TEXT_303 = NL + "\t\t\tresult.append(convert";
  protected final String TEXT_304 = "ToString(";
  protected final String TEXT_305 = ", ";
  protected final String TEXT_306 = "));";
  protected final String TEXT_307 = NL + "\t\t\tresult.append(";
  protected final String TEXT_308 = ".convert";
  protected final String TEXT_309 = "((";
  protected final String TEXT_310 = ")";
  protected final String TEXT_311 = "));";
  protected final String TEXT_312 = NL + "\t\t\tresult.append(";
  protected final String TEXT_313 = ".convertToString(";
  protected final String TEXT_314 = ", ";
  protected final String TEXT_315 = "));";
  protected final String TEXT_316 = NL + "\t\t\tresult.append(' ');" + NL + "\t\t}" + NL + "\t\treturn result.substring(0, result.length() - 1);";
  protected final String TEXT_317 = NL + "\t\tif (instanceValue == null) return null;";
  protected final String TEXT_318 = NL + "\t\tif (";
  protected final String TEXT_319 = ".isInstance(instanceValue))" + NL + "\t\t{" + NL + "\t\t\ttry" + NL + "\t\t\t{";
  protected final String TEXT_320 = NL + "\t\t\t\tString value = convert";
  protected final String TEXT_321 = "(instanceValue);";
  protected final String TEXT_322 = NL + "\t\t\t\tString value = convert";
  protected final String TEXT_323 = "(((";
  protected final String TEXT_324 = ")instanceValue).";
  protected final String TEXT_325 = "());";
  protected final String TEXT_326 = NL + "\t\t\t\tString value = convert";
  protected final String TEXT_327 = "((";
  protected final String TEXT_328 = ")instanceValue);";
  protected final String TEXT_329 = NL + "\t\t\t\tString value = convert";
  protected final String TEXT_330 = "ToString(";
  protected final String TEXT_331 = ", instanceValue);";
  protected final String TEXT_332 = NL + "\t\t\t\tString value = ";
  protected final String TEXT_333 = ".convert";
  protected final String TEXT_334 = "((";
  protected final String TEXT_335 = ")instanceValue);";
  protected final String TEXT_336 = NL + "\t\t\t\tString value = ";
  protected final String TEXT_337 = ".convertToString(";
  protected final String TEXT_338 = ", instanceValue);";
  protected final String TEXT_339 = NL + "\t\t\t\tif (value != null) return value;" + NL + "\t\t\t}" + NL + "\t\t\tcatch (Exception e)" + NL + "\t\t\t{" + NL + "\t\t\t\t// Keep trying other member types until all have failed." + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_340 = NL + "\t\ttry" + NL + "\t\t{";
  protected final String TEXT_341 = NL + "\t\t\tString value = convert";
  protected final String TEXT_342 = "(instanceValue);";
  protected final String TEXT_343 = NL + "\t\t\tString value = convert";
  protected final String TEXT_344 = "ToString(";
  protected final String TEXT_345 = ", ";
  protected final String TEXT_346 = "new ";
  protected final String TEXT_347 = "(instanceValue)";
  protected final String TEXT_348 = "instanceValue";
  protected final String TEXT_349 = ");";
  protected final String TEXT_350 = NL + "\t\t\tString value = ";
  protected final String TEXT_351 = ".convert";
  protected final String TEXT_352 = "(instanceValue);";
  protected final String TEXT_353 = NL + "\t\t\tString value = ";
  protected final String TEXT_354 = ".convertToString(";
  protected final String TEXT_355 = ", ";
  protected final String TEXT_356 = "new ";
  protected final String TEXT_357 = "(instanceValue)";
  protected final String TEXT_358 = "instanceValue";
  protected final String TEXT_359 = ");";
  protected final String TEXT_360 = NL + "\t\t\tif (value != null) return value;" + NL + "\t\t}" + NL + "\t\tcatch (Exception e)" + NL + "\t\t{" + NL + "\t\t\t// Keep trying other member types until all have failed." + NL + "\t\t}";
  protected final String TEXT_361 = NL + "\t\tthrow new IllegalArgumentException(\"Invalid value: '\"+instanceValue+\"' for datatype :\"+";
  protected final String TEXT_362 = ".getName());";
  protected final String TEXT_363 = NL + "\t\treturn super.convertToString(instanceValue);";
  protected final String TEXT_364 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new ";
  protected final String TEXT_365 = "();";
  protected final String TEXT_366 = NL + "\t\treturn super.convertToString(";
  protected final String TEXT_367 = ", new ";
  protected final String TEXT_368 = "(instanceValue));";
  protected final String TEXT_369 = NL + "\t\treturn super.convertToString(";
  protected final String TEXT_370 = ", instanceValue);";
  protected final String TEXT_371 = NL + "\t}" + NL;
  protected final String TEXT_372 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_373 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_374 = NL + "\tpublic String convert";
  protected final String TEXT_375 = "ToString(";
  protected final String TEXT_376 = " eDataType, Object instanceValue)" + NL + "\t{";
  protected final String TEXT_377 = NL + "\t\treturn convert";
  protected final String TEXT_378 = "((";
  protected final String TEXT_379 = ")instanceValue);";
  protected final String TEXT_380 = NL + "\t\treturn instanceValue == null ? null : instanceValue.toString();";
  protected final String TEXT_381 = NL + "\t\treturn convert";
  protected final String TEXT_382 = "ToString(";
  protected final String TEXT_383 = ", instanceValue);";
  protected final String TEXT_384 = NL + "\t\treturn ";
  protected final String TEXT_385 = ".convertToString(";
  protected final String TEXT_386 = ", instanceValue);";
  protected final String TEXT_387 = NL + "\t\treturn convert";
  protected final String TEXT_388 = "((";
  protected final String TEXT_389 = ")instanceValue);";
  protected final String TEXT_390 = NL + "\t\tif (instanceValue == null) return null;" + NL + "\t\t";
  protected final String TEXT_391 = " list = (";
  protected final String TEXT_392 = ")instanceValue;" + NL + "\t\tif (list.isEmpty()) return \"\";" + NL + "\t\t";
  protected final String TEXT_393 = " result = new ";
  protected final String TEXT_394 = "();";
  protected final String TEXT_395 = NL + "\t\tfor (";
  protected final String TEXT_396 = " i = list.iterator(); i.hasNext(); )";
  protected final String TEXT_397 = NL + "\t\tfor (";
  protected final String TEXT_398 = " item : list)";
  protected final String TEXT_399 = NL + "\t\t{";
  protected final String TEXT_400 = NL + "\t\t\tresult.append(convert";
  protected final String TEXT_401 = "ToString(";
  protected final String TEXT_402 = ", ";
  protected final String TEXT_403 = "));";
  protected final String TEXT_404 = NL + "\t\t\tresult.append(";
  protected final String TEXT_405 = ".convertToString(";
  protected final String TEXT_406 = ", ";
  protected final String TEXT_407 = "));";
  protected final String TEXT_408 = NL + "\t\t\tresult.append(' ');" + NL + "\t\t}" + NL + "\t\treturn result.substring(0, result.length() - 1);";
  protected final String TEXT_409 = NL + "\t\treturn instanceValue == null ? null : convert";
  protected final String TEXT_410 = "(((";
  protected final String TEXT_411 = ")instanceValue)";
  protected final String TEXT_412 = ".";
  protected final String TEXT_413 = "()";
  protected final String TEXT_414 = ");";
  protected final String TEXT_415 = NL + "\t\treturn convert";
  protected final String TEXT_416 = "(instanceValue);";
  protected final String TEXT_417 = NL + "\t\tif (instanceValue == null) return null;";
  protected final String TEXT_418 = NL + "\t\tif (";
  protected final String TEXT_419 = ".isInstance(instanceValue))" + NL + "\t\t{" + NL + "\t\t\ttry" + NL + "\t\t\t{";
  protected final String TEXT_420 = NL + "\t\t\t\tString value = convert";
  protected final String TEXT_421 = "ToString(";
  protected final String TEXT_422 = ", instanceValue);";
  protected final String TEXT_423 = NL + "\t\t\t\tString value = ";
  protected final String TEXT_424 = ".convertToString(";
  protected final String TEXT_425 = ", instanceValue);";
  protected final String TEXT_426 = NL + "\t\t\t\tif (value != null) return value;" + NL + "\t\t\t}" + NL + "\t\t\tcatch (Exception e)" + NL + "\t\t\t{" + NL + "\t\t\t\t// Keep trying other member types until all have failed." + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_427 = NL + "\t\tthrow new IllegalArgumentException(\"Invalid value: '\"+instanceValue+\"' for datatype :\"+eDataType.getName());";
  protected final String TEXT_428 = NL + "\t\treturn instanceValue == null ? null : convert";
  protected final String TEXT_429 = "(";
  protected final String TEXT_430 = "(";
  protected final String TEXT_431 = "(";
  protected final String TEXT_432 = ")instanceValue";
  protected final String TEXT_433 = ").";
  protected final String TEXT_434 = "()";
  protected final String TEXT_435 = ");";
  protected final String TEXT_436 = NL + "\t\treturn convert";
  protected final String TEXT_437 = "((";
  protected final String TEXT_438 = ")instanceValue);";
  protected final String TEXT_439 = NL + "\t\treturn super.convertToString(instanceValue);";
  protected final String TEXT_440 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new ";
  protected final String TEXT_441 = "();";
  protected final String TEXT_442 = NL + "\t\treturn super.convertToString(eDataType, instanceValue);";
  protected final String TEXT_443 = NL + "\t}" + NL;
  protected final String TEXT_444 = NL + "\t/**" + NL + "\t * Returns a new object of class '<em>";
  protected final String TEXT_445 = "</em>'." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return a new object of class '<em>";
  protected final String TEXT_446 = "</em>'." + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_447 = " create";
  protected final String TEXT_448 = "();" + NL;
  protected final String TEXT_449 = NL + "\t/**" + NL + "\t * Returns an instance of data type '<em>";
  protected final String TEXT_450 = "</em>' corresponding the given literal." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param literal a literal of the data type." + NL + "\t * @return a new instance value of the data type." + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_451 = " create";
  protected final String TEXT_452 = "(String literal);" + NL + "" + NL + "\t/**" + NL + "\t * Returns a literal representation of an instance of data type '<em>";
  protected final String TEXT_453 = "</em>'." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param instanceValue an instance value of the data type." + NL + "\t * @return a literal representation of the instance value." + NL + "\t * @generated" + NL + "\t */" + NL + "\tString convert";
  protected final String TEXT_454 = "(";
  protected final String TEXT_455 = " instanceValue);" + NL;
  protected final String TEXT_456 = NL + "\t/**" + NL + "\t * Returns the package supported by this factory." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the package supported by this factory." + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_457 = " get";
  protected final String TEXT_458 = "();" + NL;
  protected final String TEXT_459 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_460 = " get";
  protected final String TEXT_461 = "()" + NL + "\t{" + NL + "\t\treturn (";
  protected final String TEXT_462 = ")getEPackage();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @deprecated" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_463 = NL + "\t@Deprecated";
  protected final String TEXT_464 = NL + "\tpublic static ";
  protected final String TEXT_465 = " getPackage()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_466 = ".eINSTANCE;" + NL + "\t}" + NL;
  protected final String TEXT_467 = NL + "} //";
  protected final String TEXT_468 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * Copyright (c) 2002-2010 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */

    GenPackage genPackage = (GenPackage)((Object[])argument)[0]; GenModel genModel=genPackage.getGenModel(); /* Trick to import java.util.* without warnings */Iterator.class.getName();
    boolean isInterface = Boolean.TRUE.equals(((Object[])argument)[1]); boolean isImplementation = Boolean.TRUE.equals(((Object[])argument)[2]);
    String publicStaticFinalFlag = isImplementation ? "public static final " : "";
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    {GenBase copyrightHolder = argument instanceof GenBase ? (GenBase)argument : argument instanceof Object[] && ((Object[])argument)[0] instanceof GenBase ? (GenBase)((Object[])argument)[0] : null;
    if (copyrightHolder != null && copyrightHolder.hasCopyright()) {
    stringBuffer.append(TEXT_3);
    stringBuffer.append(copyrightHolder.getCopyright(copyrightHolder.getGenModel().getIndentation(stringBuffer)));
    }}
    stringBuffer.append(TEXT_4);
    if (isInterface || genModel.isSuppressInterfaces()) {
    stringBuffer.append(TEXT_5);
    stringBuffer.append(genPackage.getReflectionPackageName());
    stringBuffer.append(TEXT_6);
    } else {
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genPackage.getClassPackageName());
    stringBuffer.append(TEXT_8);
    }
    stringBuffer.append(TEXT_9);
    if (isImplementation) {
    genModel.addPseudoImport("org.eclipse.emf.ecore.impl.MinimalEObjectImpl.Container");
    genModel.addPseudoImport("org.eclipse.emf.ecore.impl.MinimalEObjectImpl.Container.Dynamic");
    genModel.addImport("org.eclipse.emf.ecore.EClass");
    genModel.addImport("org.eclipse.emf.ecore.EObject");
    if (!genPackage.hasJavaLangConflict() && !genPackage.hasInterfaceImplConflict() && !genPackage.getClassPackageName().equals(genPackage.getInterfacePackageName())) genModel.addImport(genPackage.getInterfacePackageName() + ".*");
    }
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_10);
    if (isInterface) {
    stringBuffer.append(TEXT_11);
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_12);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    }
    stringBuffer.append(TEXT_13);
    } else {
    stringBuffer.append(TEXT_14);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genPackage.getFactoryClassName());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.EFactoryImpl"));
    if (!genModel.isSuppressInterfaces()) {
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genPackage.getImportedFactoryInterfaceName());
    }
    } else {
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genPackage.getFactoryInterfaceName());
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EFactory"));
    }
    }
    stringBuffer.append(TEXT_20);
    if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_21);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_24);
    }
    if (isImplementation && (genModel.isSuppressEMFMetaData() || genModel.isSuppressInterfaces())) {
    stringBuffer.append(TEXT_25);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genPackage.getFactoryClassName());
    stringBuffer.append(TEXT_26);
    }
    if (isInterface && genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_27);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genPackage.getFactoryInterfaceName());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(genPackage.getQualifiedFactoryClassName());
    stringBuffer.append(TEXT_29);
    } else if (isInterface && !genModel.isSuppressInterfaces()) {
    stringBuffer.append(TEXT_30);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genPackage.getFactoryInterfaceName());
    stringBuffer.append(TEXT_31);
    stringBuffer.append(genPackage.getQualifiedFactoryClassName());
    stringBuffer.append(TEXT_32);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_33);
    String factoryType = genModel.isSuppressEMFMetaData() ? genPackage.getFactoryClassName() : genPackage.getImportedFactoryInterfaceName();
    stringBuffer.append(TEXT_34);
    stringBuffer.append(factoryType);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(factoryType);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genPackage.getFactoryName());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(factoryType);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_40);
    stringBuffer.append(genPackage.getFactoryName());
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genPackage.getFactoryName());
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genPackage.getImportedFactoryClassName());
    stringBuffer.append(TEXT_44);
    stringBuffer.append(genPackage.getFactoryClassName());
    stringBuffer.append(TEXT_45);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_46);
    }
    stringBuffer.append(TEXT_47);
    for (GenClass genClass : genPackage.getGenClasses()) {
    if (!genClass.isAbstract()) {
    stringBuffer.append(TEXT_48);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_49);
    stringBuffer.append(genClass.getClassifierID());
    stringBuffer.append(TEXT_50);
    stringBuffer.append(!genClass.isEObjectExtension() ? "(EObject)" : "" );
    stringBuffer.append(TEXT_51);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_52);
    }
    }
    stringBuffer.append(TEXT_53);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_54);
    if (!genPackage.getAllGenDataTypes().isEmpty()) {
    stringBuffer.append(TEXT_55);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_56);
    }
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_58);
    for (GenDataType genDataType : genPackage.getAllGenDataTypes()) {
    if (genDataType.isSerializable()) {
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genDataType.getClassifierID());
    stringBuffer.append(TEXT_61);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_62);
    }
    }
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_64);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_65);
    }
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_67);
    for (GenDataType genDataType : genPackage.getAllGenDataTypes()) {
    if (genDataType.isSerializable()) {
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genDataType.getClassifierID());
    stringBuffer.append(TEXT_70);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_71);
    }
    }
    stringBuffer.append(TEXT_72);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_73);
    }
    for (GenClass genClass : genPackage.getGenClasses()) {
    if (!genClass.isAbstract()) {
    stringBuffer.append(TEXT_74);
    stringBuffer.append(genClass.getTypeParameters());
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceTypeArguments());
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_76);
    if (genClass.isDynamic()) {
    stringBuffer.append(TEXT_77);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceTypeArguments());
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_79);
    stringBuffer.append(genClass.getCastFromEObject());
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_81);
    } else {
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genClass.getImportedClassName());
    stringBuffer.append(genClass.getClassTypeArguments());
    stringBuffer.append(TEXT_83);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genClass.getImportedClassName());
    stringBuffer.append(genClass.getClassTypeArguments());
    stringBuffer.append(TEXT_85);
    if (genModel.isSuppressInterfaces() && !genPackage.getReflectionPackageName().equals(genPackage.getInterfacePackageName())) {
    stringBuffer.append(TEXT_86);
    }
    stringBuffer.append(TEXT_87);
    }
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_89);
    }
    }
    for (GenDataType genDataType : genPackage.getAllGenDataTypes()) {
    if (genDataType.isSerializable()) {
    if (genPackage.isDataTypeConverters() || genDataType.hasCreatorBody()) { String eDataType = genDataType.getQualifiedClassifierAccessor();
    stringBuffer.append(TEXT_90);
    if (genModel.useGenerics() && genDataType.isUncheckedCast() && !genDataType.hasCreatorBody()) {
    stringBuffer.append(TEXT_91);
    }
    stringBuffer.append(TEXT_92);
    stringBuffer.append(genDataType.getImportedParameterizedInstanceClassName());
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_94);
    if (genDataType.hasCreatorBody()) {
    stringBuffer.append(TEXT_95);
    }
    stringBuffer.append(TEXT_96);
    if (genDataType.hasCreatorBody()) {
    stringBuffer.append(TEXT_97);
    } else {
    stringBuffer.append(TEXT_98);
    }
    stringBuffer.append(TEXT_99);
    if (genDataType.hasCreatorBody()) {
    stringBuffer.append(TEXT_100);
    stringBuffer.append(genDataType.getCreatorBody(genModel.getIndentation(stringBuffer)));
    } else if (genDataType instanceof GenEnum) {
    stringBuffer.append(TEXT_101);
    stringBuffer.append(genDataType.getImportedInstanceClassName());
    stringBuffer.append(TEXT_102);
    stringBuffer.append(genDataType.getImportedInstanceClassName());
    stringBuffer.append(TEXT_103);
    stringBuffer.append(eDataType);
    stringBuffer.append(TEXT_104);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(genModel.getNonNLS(3));
    stringBuffer.append(TEXT_105);
    } else if (genDataType.getBaseType() != null) { GenDataType genBaseType = genDataType.getBaseType(); boolean isPrimitiveConversion = !genDataType.isPrimitiveType() && genBaseType.isPrimitiveType();
    if (genBaseType.getGenPackage() == genPackage) {
    if (isPrimitiveConversion && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_106);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_108);
    } else {
    stringBuffer.append(TEXT_109);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_110);
    }
    } else if (genBaseType.getGenPackage().isDataTypeConverters()) {
    if (isPrimitiveConversion && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_111);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_112);
    stringBuffer.append(genBaseType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_113);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_114);
    } else {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genBaseType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_117);
    }
    } else {
    stringBuffer.append(TEXT_118);
    if (!genDataType.isObjectType()) {
    stringBuffer.append(TEXT_119);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_120);
    }
    stringBuffer.append(genBaseType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_121);
    stringBuffer.append(genBaseType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_122);
    }
    } else if (genDataType.getItemType() != null) { GenDataType genItemType = genDataType.getItemType(); 
    stringBuffer.append(TEXT_123);
    stringBuffer.append(genDataType.getImportedParameterizedInstanceClassName());
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genModel.getImportedName("java.util.ArrayList"));
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genItemType.getObjectType().getImportedParameterizedInstanceClassName());
    stringBuffer.append(TEXT_126);
    }
    stringBuffer.append(TEXT_127);
    if (genModel.getRuntimeVersion().getValue() < GenRuntimeVersion.EMF26_VALUE) {
    stringBuffer.append(TEXT_128);
    stringBuffer.append(genModel.getImportedName("java.util.StringTokenizer"));
    stringBuffer.append(TEXT_129);
    stringBuffer.append(genModel.getImportedName("java.util.StringTokenizer"));
    stringBuffer.append(TEXT_130);
    } else {
    stringBuffer.append(TEXT_131);
    }
    stringBuffer.append(TEXT_132);
    if (genModel.getRuntimeVersion().getValue() < GenRuntimeVersion.EMF26_VALUE) {
    stringBuffer.append(TEXT_133);
    }
    if (genItemType.getGenPackage() == genPackage) {
    if (genPackage.isDataTypeConverters()) { genItemType = genItemType.getObjectType();
    stringBuffer.append(TEXT_134);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_135);
    } else {
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_137);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_138);
    }
    } else {
    if (genItemType.getGenPackage().isDataTypeConverters()) { genItemType = genItemType.getObjectType();
    stringBuffer.append(TEXT_139);
    stringBuffer.append(genItemType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_140);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_141);
    } else {
    stringBuffer.append(TEXT_142);
    stringBuffer.append(genItemType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_144);
    }
    }
    stringBuffer.append(TEXT_145);
    } else if (!genDataType.getMemberTypes().isEmpty()) {
    stringBuffer.append(TEXT_146);
    stringBuffer.append(genDataType.getStaticValue(null));
    stringBuffer.append(TEXT_147);
    stringBuffer.append(genDataType.getImportedInstanceClassName());
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genDataType.getStaticValue(null));
    stringBuffer.append(TEXT_149);
    for (GenDataType genMemberType : genDataType.getMemberTypes()) {
    stringBuffer.append(TEXT_150);
    if (genMemberType.getGenPackage() == genPackage) {
    if (genPackage.isDataTypeConverters()) { if (!genDataType.isPrimitiveType()) genMemberType = genMemberType.getObjectType();
    stringBuffer.append(TEXT_151);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_152);
    } else {
    stringBuffer.append(TEXT_153);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_155);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_156);
    }
    } else {
    if (genPackage.isDataTypeConverters()) { if (!genDataType.isPrimitiveType()) genMemberType = genMemberType.getObjectType();
    stringBuffer.append(TEXT_157);
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_158);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_159);
    } else {
    stringBuffer.append(TEXT_160);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_161);
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_162);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_163);
    }
    }
    stringBuffer.append(TEXT_164);
    if (!genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_165);
    }
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.Diagnostician"));
    stringBuffer.append(TEXT_166);
    stringBuffer.append(eDataType);
    stringBuffer.append(TEXT_167);
    if (genDataType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_168);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_169);
    } else {
    stringBuffer.append(TEXT_170);
    }
    stringBuffer.append(TEXT_171);
    }
    stringBuffer.append(TEXT_172);
    if (!genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_173);
    }
    stringBuffer.append(TEXT_174);
    } else if (!genDataType.hasConversionDelegate() && genModel.useGenerics() && (genDataType.isArrayType() || !genDataType.getEcoreDataType().getETypeParameters().isEmpty() || genDataType.getEcoreDataType().getInstanceTypeName().contains("<"))) {
    stringBuffer.append(TEXT_175);
    stringBuffer.append(genDataType.getImportedParameterizedObjectInstanceClassName());
    stringBuffer.append(TEXT_176);
    } else if (!genDataType.hasConversionDelegate() && genDataType.isArrayType()) {
    stringBuffer.append(TEXT_177);
    stringBuffer.append(genModel.getImportedName("java.lang.UnsupportedOperationException"));
    stringBuffer.append(TEXT_178);
    } else if (genDataType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_179);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_180);
    stringBuffer.append(eDataType);
    stringBuffer.append(TEXT_181);
    stringBuffer.append(genDataType.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_182);
    } else {
    stringBuffer.append(TEXT_183);
    if (!genDataType.isObjectType()) {
    stringBuffer.append(TEXT_184);
    stringBuffer.append(genDataType.getImportedParameterizedObjectInstanceClassName());
    stringBuffer.append(TEXT_185);
    }
    stringBuffer.append(TEXT_186);
    stringBuffer.append(eDataType);
    stringBuffer.append(TEXT_187);
    }
    stringBuffer.append(TEXT_188);
    }
    stringBuffer.append(TEXT_189);
    if (!genPackage.isDataTypeConverters() && genModel.useGenerics() && genDataType.isUncheckedCast() && !genDataType.hasCreatorBody()) {
    stringBuffer.append(TEXT_190);
    }
    stringBuffer.append(TEXT_191);
    stringBuffer.append(genDataType.getImportedParameterizedObjectInstanceClassName());
    stringBuffer.append(TEXT_192);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_193);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_194);
    if (genDataType instanceof GenEnum) {
    if (genPackage.isDataTypeConverters() || genDataType.hasCreatorBody()) {
    stringBuffer.append(TEXT_195);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_196);
    } else {
    stringBuffer.append(TEXT_197);
    stringBuffer.append(((GenEnum)genDataType).getImportedInstanceClassName());
    stringBuffer.append(TEXT_198);
    stringBuffer.append(((GenEnum)genDataType).getImportedInstanceClassName());
    stringBuffer.append(TEXT_199);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(genModel.getNonNLS(3));
    stringBuffer.append(TEXT_200);
    }
    } else if (genDataType.getBaseType() != null) { GenDataType genBaseType = genDataType.getBaseType(); 
    if (genBaseType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_201);
    if (!genDataType.getObjectInstanceClassName().equals(genBaseType.getObjectInstanceClassName())) {
    stringBuffer.append(TEXT_202);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_203);
    }
    stringBuffer.append(TEXT_204);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_205);
    stringBuffer.append(genBaseType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_206);
    } else {
    stringBuffer.append(TEXT_207);
    if (!genDataType.isObjectType()) {
    stringBuffer.append(TEXT_208);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_209);
    }
    stringBuffer.append(genBaseType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_210);
    stringBuffer.append(genBaseType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_211);
    }
    } else if (genDataType.getItemType() != null) { GenDataType genItemType = genDataType.getItemType(); 
    if (genPackage.isDataTypeConverters()) {
    stringBuffer.append(TEXT_212);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_213);
    } else {
    stringBuffer.append(TEXT_214);
    stringBuffer.append(genDataType.getImportedParameterizedObjectInstanceClassName());
    stringBuffer.append(TEXT_215);
    stringBuffer.append(genModel.getImportedName("java.util.ArrayList"));
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_216);
    stringBuffer.append(genItemType.getObjectType().getImportedParameterizedInstanceClassName());
    stringBuffer.append(TEXT_217);
    }
    stringBuffer.append(TEXT_218);
    if (genModel.getRuntimeVersion().getValue() < GenRuntimeVersion.EMF26_VALUE) {
    stringBuffer.append(TEXT_219);
    stringBuffer.append(genModel.getImportedName("java.util.StringTokenizer"));
    stringBuffer.append(TEXT_220);
    stringBuffer.append(genModel.getImportedName("java.util.StringTokenizer"));
    stringBuffer.append(TEXT_221);
    } else {
    stringBuffer.append(TEXT_222);
    }
    stringBuffer.append(TEXT_223);
    if (genModel.getRuntimeVersion().getValue() < GenRuntimeVersion.EMF26_VALUE) {
    stringBuffer.append(TEXT_224);
    }
    if (genItemType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_225);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_226);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_227);
    } else {
    stringBuffer.append(TEXT_228);
    if (!genItemType.isObjectType()) {
    stringBuffer.append(TEXT_229);
    stringBuffer.append(genItemType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_230);
    }
    stringBuffer.append(genItemType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_231);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_232);
    }
    stringBuffer.append(TEXT_233);
    }
    } else if (!genDataType.getMemberTypes().isEmpty()) {
    if (genPackage.isDataTypeConverters()) {
    if (genDataType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_234);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_235);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_236);
    } else {
    stringBuffer.append(TEXT_237);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_238);
    }
    } else {
    stringBuffer.append(TEXT_239);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_240);
    for (GenDataType genMemberType : genDataType.getMemberTypes()) {
    stringBuffer.append(TEXT_241);
    if (genMemberType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_242);
    if (!genDataType.isObjectType() && !genDataType.getObjectInstanceClassName().equals(genMemberType.getObjectInstanceClassName())) {
    stringBuffer.append(TEXT_243);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_244);
    }
    stringBuffer.append(TEXT_245);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_246);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_247);
    } else {
    stringBuffer.append(TEXT_248);
    if (!genDataType.isObjectType()) {
    stringBuffer.append(TEXT_249);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_250);
    }
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_251);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_252);
    }
    stringBuffer.append(TEXT_253);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.Diagnostician"));
    stringBuffer.append(TEXT_254);
    }
    stringBuffer.append(TEXT_255);
    }
    } else if (genPackage.isDataTypeConverters() || genDataType.hasCreatorBody()) {
    stringBuffer.append(TEXT_256);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_257);
    } else if (!genDataType.hasConversionDelegate() && genModel.useGenerics() && (genDataType.isArrayType() || !genDataType.getEcoreDataType().getETypeParameters().isEmpty() || genDataType.getEcoreDataType().getInstanceTypeName().contains("<"))) {
    stringBuffer.append(TEXT_258);
    if (!genDataType.isObjectType()) {
    stringBuffer.append(TEXT_259);
    stringBuffer.append(genDataType.getImportedParameterizedObjectInstanceClassName());
    stringBuffer.append(TEXT_260);
    }
    stringBuffer.append(TEXT_261);
    } else if (!genDataType.hasConversionDelegate() && genDataType.isArrayType()) {
    stringBuffer.append(TEXT_262);
    stringBuffer.append(genModel.getImportedName("java.lang.UnsupportedOperationException"));
    stringBuffer.append(TEXT_263);
    } else {
    stringBuffer.append(TEXT_264);
    if (!genDataType.isObjectType()) {
    stringBuffer.append(TEXT_265);
    stringBuffer.append(genDataType.getImportedParameterizedObjectInstanceClassName());
    stringBuffer.append(TEXT_266);
    }
    stringBuffer.append(TEXT_267);
    }
    stringBuffer.append(TEXT_268);
    if (genPackage.isDataTypeConverters() || genDataType.hasConverterBody()) { String eDataType = genDataType.getQualifiedClassifierAccessor();
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_270);
    if (genDataType.hasCreatorBody()) {
    stringBuffer.append(TEXT_271);
    }
    stringBuffer.append(genDataType.getImportedBoundedWildcardInstanceClassName());
    stringBuffer.append(TEXT_272);
    if (genDataType.hasConverterBody()) {
    stringBuffer.append(TEXT_273);
    } else {
    stringBuffer.append(TEXT_274);
    }
    stringBuffer.append(TEXT_275);
    if (genDataType.hasConverterBody()) {
    stringBuffer.append(TEXT_276);
    stringBuffer.append(genDataType.getConverterBody(genModel.getIndentation(stringBuffer)));
    } else if (genDataType instanceof GenEnum) {
    stringBuffer.append(TEXT_277);
    } else if (genDataType.getBaseType() != null) { GenDataType genBaseType = genDataType.getBaseType(); boolean isPrimitiveConversion = !genDataType.isPrimitiveType() && genBaseType.isPrimitiveType();
    if (genBaseType.getGenPackage() == genPackage) {
    if (isPrimitiveConversion) {
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_279);
    if (genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_280);
    stringBuffer.append(genBaseType.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_281);
    }
    stringBuffer.append(TEXT_282);
    } else {
    stringBuffer.append(TEXT_283);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_284);
    }
    } else if (genBaseType.getGenPackage().isDataTypeConverters()) {
    stringBuffer.append(TEXT_285);
    stringBuffer.append(genBaseType.getGenPackage().getQualifiedFactoryInstanceAccessor());
    stringBuffer.append(TEXT_286);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_287);
    } else {
    stringBuffer.append(TEXT_288);
    stringBuffer.append(genBaseType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_289);
    stringBuffer.append(genBaseType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_290);
    }
    } else if (genDataType.getItemType() != null) { GenDataType genItemType = genDataType.getItemType(); 
    stringBuffer.append(TEXT_291);
    stringBuffer.append(genModel.getImportedName("java.lang.StringBuffer"));
    stringBuffer.append(TEXT_292);
    stringBuffer.append(genModel.getImportedName("java.lang.StringBuffer"));
    stringBuffer.append(TEXT_293);
    String item; if (!genModel.useGenerics()) { item = "i.next()"; 
    stringBuffer.append(TEXT_294);
    stringBuffer.append(genModel.getImportedName("java.util.Iterator"));
    stringBuffer.append(TEXT_295);
    } else { item = "item";
    stringBuffer.append(TEXT_296);
    stringBuffer.append(genModel.getImportedName("java.lang.Object"));
    stringBuffer.append(TEXT_297);
    }
    stringBuffer.append(TEXT_298);
    if (genItemType.getGenPackage() == genPackage) {
    if (genPackage.isDataTypeConverters()) { genItemType = genItemType.getObjectType();
    stringBuffer.append(TEXT_299);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_300);
    stringBuffer.append(genItemType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_301);
    stringBuffer.append(item);
    stringBuffer.append(TEXT_302);
    } else {
    stringBuffer.append(TEXT_303);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_304);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_305);
    stringBuffer.append(item);
    stringBuffer.append(TEXT_306);
    }
    } else {
    if (genItemType.getGenPackage().isDataTypeConverters()) { genItemType = genItemType.getObjectType();
    stringBuffer.append(TEXT_307);
    stringBuffer.append(genItemType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_308);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_309);
    stringBuffer.append(genItemType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_310);
    stringBuffer.append(item);
    stringBuffer.append(TEXT_311);
    } else {
    stringBuffer.append(TEXT_312);
    stringBuffer.append(genItemType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_313);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_314);
    stringBuffer.append(item);
    stringBuffer.append(TEXT_315);
    }
    }
    stringBuffer.append(TEXT_316);
    } else if (!genDataType.getMemberTypes().isEmpty()) {
    if (!genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_317);
    for (GenDataType genMemberType : genDataType.getMemberTypes()) {
    stringBuffer.append(TEXT_318);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_319);
    if (genMemberType.getGenPackage() == genPackage) {
    if (genPackage.isDataTypeConverters()) {
    if (genMemberType.getQualifiedInstanceClassName().equals(genDataType.getQualifiedInstanceClassName())) {
    stringBuffer.append(TEXT_320);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_321);
    } else if (genMemberType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_322);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_323);
    stringBuffer.append(genMemberType.getObjectType().getImportedInstanceClassName());
    stringBuffer.append(TEXT_324);
    stringBuffer.append(genMemberType.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_325);
    } else {
    stringBuffer.append(TEXT_326);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_327);
    stringBuffer.append(genMemberType.getObjectType().getImportedBoundedWildcardInstanceClassName());
    stringBuffer.append(TEXT_328);
    }
    } else {
    stringBuffer.append(TEXT_329);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_330);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_331);
    }
    } else {
    if (genMemberType.getGenPackage().isDataTypeConverters()) { genMemberType = genMemberType.getObjectType();
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_333);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_334);
    stringBuffer.append(genMemberType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_335);
    } else {
    stringBuffer.append(TEXT_336);
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_337);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_338);
    }
    }
    stringBuffer.append(TEXT_339);
    }
    } else {
    for (GenDataType genMemberType : genDataType.getMemberTypes()) {
    stringBuffer.append(TEXT_340);
    if (genMemberType.getGenPackage() == genPackage) {
    if (genPackage.isDataTypeConverters()) {
    stringBuffer.append(TEXT_341);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_342);
    } else {
    stringBuffer.append(TEXT_343);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_344);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_345);
    if (genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_346);
    stringBuffer.append(genMemberType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_347);
    } else {
    stringBuffer.append(TEXT_348);
    }
    stringBuffer.append(TEXT_349);
    }
    } else {
    if (genMemberType.getGenPackage().isDataTypeConverters()) {
    stringBuffer.append(TEXT_350);
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_351);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_352);
    } else {
    stringBuffer.append(TEXT_353);
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_354);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_355);
    if (genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_356);
    stringBuffer.append(genMemberType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_357);
    } else {
    stringBuffer.append(TEXT_358);
    }
    stringBuffer.append(TEXT_359);
    }
    }
    stringBuffer.append(TEXT_360);
    }
    }
    stringBuffer.append(TEXT_361);
    stringBuffer.append(eDataType);
    stringBuffer.append(TEXT_362);
    } else if (!genDataType.hasConversionDelegate() && genModel.useGenerics() && (genDataType.isArrayType() || !genDataType.getEcoreDataType().getETypeParameters().isEmpty() || genDataType.getEcoreDataType().getInstanceTypeName().contains("<"))) {
    stringBuffer.append(TEXT_363);
    } else if (!genDataType.hasConversionDelegate() && genDataType.isArrayType()) {
    stringBuffer.append(TEXT_364);
    stringBuffer.append(genModel.getImportedName("java.lang.UnsupportedOperationException"));
    stringBuffer.append(TEXT_365);
    } else if (genDataType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_366);
    stringBuffer.append(eDataType);
    stringBuffer.append(TEXT_367);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_368);
    } else {
    stringBuffer.append(TEXT_369);
    stringBuffer.append(eDataType);
    stringBuffer.append(TEXT_370);
    }
    stringBuffer.append(TEXT_371);
    }
    stringBuffer.append(TEXT_372);
    if (genModel.useGenerics() && (genDataType.getItemType() != null || genDataType.isUncheckedCast()) && (genPackage.isDataTypeConverters() || genDataType.hasCreatorBody())) {
    stringBuffer.append(TEXT_373);
    }
    stringBuffer.append(TEXT_374);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_375);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_376);
    if (genDataType instanceof GenEnum) {
    if (genPackage.isDataTypeConverters() || genDataType.hasConverterBody()) {
    stringBuffer.append(TEXT_377);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_378);
    stringBuffer.append(genDataType.getImportedInstanceClassName());
    stringBuffer.append(TEXT_379);
    } else {
    stringBuffer.append(TEXT_380);
    }
    } else if (genDataType.getBaseType() != null) { GenDataType genBaseType = genDataType.getBaseType(); 
    if (genBaseType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_381);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_382);
    stringBuffer.append(genBaseType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_383);
    } else {
    stringBuffer.append(TEXT_384);
    stringBuffer.append(genBaseType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_385);
    stringBuffer.append(genBaseType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_386);
    }
    } else if (genDataType.getItemType() != null) { GenDataType genItemType = genDataType.getItemType(); 
    if (genPackage.isDataTypeConverters() || genDataType.hasCreatorBody()) {
    stringBuffer.append(TEXT_387);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_388);
    stringBuffer.append(genDataType.getImportedBoundedWildcardInstanceClassName());
    stringBuffer.append(TEXT_389);
    } else { final String singleWildcard = genModel.useGenerics() ? "<?>" : "";
    stringBuffer.append(TEXT_390);
    stringBuffer.append(genModel.getImportedName("java.util.List"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_391);
    stringBuffer.append(genModel.getImportedName("java.util.List"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_392);
    stringBuffer.append(genModel.getImportedName("java.lang.StringBuffer"));
    stringBuffer.append(TEXT_393);
    stringBuffer.append(genModel.getImportedName("java.lang.StringBuffer"));
    stringBuffer.append(TEXT_394);
    String item; if (!genModel.useGenerics()) { item = "i.next()"; 
    stringBuffer.append(TEXT_395);
    stringBuffer.append(genModel.getImportedName("java.util.Iterator"));
    stringBuffer.append(TEXT_396);
    } else { item = "item";
    stringBuffer.append(TEXT_397);
    stringBuffer.append(genModel.getImportedName("java.lang.Object"));
    stringBuffer.append(TEXT_398);
    }
    stringBuffer.append(TEXT_399);
    if (genItemType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_400);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_401);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_402);
    stringBuffer.append(item);
    stringBuffer.append(TEXT_403);
    } else {
    stringBuffer.append(TEXT_404);
    stringBuffer.append(genItemType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_405);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_406);
    stringBuffer.append(item);
    stringBuffer.append(TEXT_407);
    }
    stringBuffer.append(TEXT_408);
    }
    } else if (!genDataType.getMemberTypes().isEmpty()) {
    if (genPackage.isDataTypeConverters() || genDataType.hasConverterBody()) {
    if (genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_409);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_410);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_411);
    if (genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_412);
    stringBuffer.append(genDataType.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_413);
    }
    stringBuffer.append(TEXT_414);
    } else {
    stringBuffer.append(TEXT_415);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_416);
    }
    } else {
    stringBuffer.append(TEXT_417);
    for (GenDataType genMemberType : genDataType.getMemberTypes()) {
    stringBuffer.append(TEXT_418);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_419);
    if (genMemberType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_420);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_421);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_422);
    } else {
    stringBuffer.append(TEXT_423);
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_424);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_425);
    }
    stringBuffer.append(TEXT_426);
    }
    stringBuffer.append(TEXT_427);
    }
    } else if (genPackage.isDataTypeConverters() || genDataType.hasConverterBody()) {
    if (genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_428);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_429);
    if (genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_430);
    }
    stringBuffer.append(TEXT_431);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_432);
    if (genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_433);
    stringBuffer.append(genDataType.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_434);
    }
    stringBuffer.append(TEXT_435);
    } else {
    stringBuffer.append(TEXT_436);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_437);
    stringBuffer.append(genDataType.getImportedBoundedWildcardInstanceClassName());
    stringBuffer.append(TEXT_438);
    }
    } else if (!genDataType.hasConversionDelegate() && genModel.useGenerics() && (genDataType.isArrayType() || !genDataType.getEcoreDataType().getETypeParameters().isEmpty() || genDataType.getEcoreDataType().getInstanceTypeName().contains("<"))) {
    stringBuffer.append(TEXT_439);
    } else if (!genDataType.hasConversionDelegate() && genDataType.isArrayType()) {
    stringBuffer.append(TEXT_440);
    stringBuffer.append(genModel.getImportedName("java.lang.UnsupportedOperationException"));
    stringBuffer.append(TEXT_441);
    } else {
    stringBuffer.append(TEXT_442);
    }
    stringBuffer.append(TEXT_443);
    }
    }
    } else {
    for (GenClass genClass : genPackage.getGenClasses()) {
    if (genClass.hasFactoryInterfaceCreateMethod()) {
    stringBuffer.append(TEXT_444);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_445);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_446);
    stringBuffer.append(genClass.getTypeParameters());
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceTypeArguments());
    stringBuffer.append(TEXT_447);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_448);
    }
    }
    if (genPackage.isDataTypeConverters()) {
    for (GenDataType genDataType : genPackage.getAllGenDataTypes()) {
    if (genDataType.isSerializable()) {
    stringBuffer.append(TEXT_449);
    stringBuffer.append(genDataType.getFormattedName());
    stringBuffer.append(TEXT_450);
    stringBuffer.append(genDataType.getImportedParameterizedInstanceClassName());
    stringBuffer.append(TEXT_451);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_452);
    stringBuffer.append(genDataType.getFormattedName());
    stringBuffer.append(TEXT_453);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_454);
    stringBuffer.append(genDataType.getImportedBoundedWildcardInstanceClassName());
    stringBuffer.append(TEXT_455);
    }
    }
    }
    }
    if (!isImplementation && !genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_456);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_457);
    stringBuffer.append(genPackage.getBasicPackageName());
    stringBuffer.append(TEXT_458);
    } else if (isImplementation) {
    stringBuffer.append(TEXT_459);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_460);
    stringBuffer.append(genPackage.getBasicPackageName());
    stringBuffer.append(TEXT_461);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_462);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_463);
    }
    stringBuffer.append(TEXT_464);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_465);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_466);
    }
    stringBuffer.append(TEXT_467);
    stringBuffer.append(isInterface ? genPackage.getFactoryInterfaceName() : genPackage.getFactoryClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_468);
    return stringBuffer.toString();
  }
}
