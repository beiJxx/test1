/*
 * 文 件 名:  Test1.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  james
 * 创建时间:  2016年4月14日
 */
package test.dynamicmethod;

import java.lang.reflect.Type;

/**
 * <一句话功能简述>
 *  
 * @author  james
 * @version  [V1.00, 2016年4月14日]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class Test1 {
	public static FastInvokeHandler GetMethodInvoker(MethodInfo methodInfo)
	{
	    DynamicMethod dynamicMethod = new DynamicMethod(string.Empty, 
	                     typeof(object), new Type[] { typeof(object), 
	                     typeof(object[]) }, 
	                     methodInfo.DeclaringType.Module);
	    ILGenerator il = dynamicMethod.GetILGenerator();
	    ParameterInfo[] ps = methodInfo.GetParameters();
	    Type[] paramTypes = new Type[ps.Length];
	    for (int i = 0; i < paramTypes.Length; i++)
	    {
	        paramTypes[i] = ps[i].ParameterType;
	    }
	    LocalBuilder[] locals = new LocalBuilder[paramTypes.Length];
	    for (int i = 0; i < paramTypes.Length; i++)
	    {
	        locals[i] = il.DeclareLocal(paramTypes[i]);
	    }
	    for (int i = 0; i < paramTypes.Length; i++)
	    {
	        il.Emit(OpCodes.Ldarg_1);
	        EmitFastInt(il, i);
	        il.Emit(OpCodes.Ldelem_Ref);
	        EmitCastToReference(il, paramTypes[i]);
	        il.Emit(OpCodes.Stloc, locals[i]);
	    }
	    il.Emit(OpCodes.Ldarg_0);
	    for (int i = 0; i < paramTypes.Length; i++)
	    {
	        il.Emit(OpCodes.Ldloc, locals[i]);
	    }
	    il.EmitCall(OpCodes.Call, methodInfo, null);
	    if (methodInfo.ReturnType == typeof(void))
	        il.Emit(OpCodes.Ldnull);
	    else
	        EmitBoxIfNeeded(il, methodInfo.ReturnType);
	    il.Emit(OpCodes.Ret);
	    FastInvokeHandler invoder = 
	      (FastInvokeHandler)dynamicMethod.CreateDelegate(typeof(FastInvokeHandler));
	    return invoder;
	}
}
