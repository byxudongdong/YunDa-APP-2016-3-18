#include <jni.h>
#include "yunda_code.c"
#include "com_yundaW16setting_cn_MyNative.h"
JNIEXPORT jint JNICALL Java_com_yundaW16setting_cn_MyNative_CheckBarcode(
		JNIEnv *env, jclass cls, jbyteArray pCode, jint codeType) {
	char* key = (char*) env->GetByteArrayElements(pCode, 0);
	int ret;

	ret = OpCom_CheckBarcode(key, codeType);
	return ret;
}
char* jstringTostring(JNIEnv* env, jstring jstr) {
	char* rtn = NULL;
	jclass clsstring = env->FindClass("java/lang/String");
	jstring strencode = env->NewStringUTF("utf-8");
	jmethodID mid = env->GetMethodID(clsstring, "getBytes",
			"(Ljava/lang/String;)[B");
	jbyteArray barr = (jbyteArray) env->CallObjectMethod(jstr, mid, strencode);
	jsize alen = env->GetArrayLength(barr);
	jbyte* ba = env->GetByteArrayElements(barr, JNI_FALSE);
	if (alen > 0) {
		rtn = (char*) malloc(alen + 1);
		memcpy(rtn, ba, alen);
		rtn[alen] = 0;
	}
	env->ReleaseByteArrayElements(barr, ba, 0);
	return rtn;
}
//JNIEXPORT jint JNICALL Java_com_yundaW16setting_cn_MyNative_cutCode
//  (JNIEnv *env, jclass cls, jbyteArray code, jint code_length, jint, jstring receive_code) {
//	char* key = (char*) env->GetByteArrayElements(code, 0);
////	char* receive = jstringTostring(env,receive_code)
//	int ret;
//	ret = cutCode(key,code_length,receive_code);
//	return ret;
//}

JNIEXPORT jint JNICALL Java_com_yundaW16setting_cn_MyNative_cutCode
  (JNIEnv *env, jclass cls, jbyteArray code) {
	char* key = (char*) env->GetByteArrayElements(code, 0);
	char pCode[32];
	int ret;
	memset(pCode,0,sizeof(pCode));
	memcpy(pCode,key,env->GetArrayLength(code));
	ret = cutCode(pCode);
	env->ReleaseByteArrayElements(code,(jbyte*)key,0);
	return  ret;
}

