
#define HY_OK 0
#define HY_ERROR -1
#include "stdio.h"
#include "string.h"
typedef char U8;

/*==============条码规则===================*/
typedef enum _barcode_type_e
{
	TYPE_EMPLOYEID=1,	//员工ID
	TYPE_COMPANYID,		//网点编号
	TYPE_BARCODE,		//运单号
	TYPE_CARID,			//车辆吗
	TYPE_CARLINE,		//车线码
	TYPE_PACKET,		//大包号
	TYPE_BAKCODE,		//回单号
	TYPE_TAG,			//封签
	TYPE_GEKOU,			//隔口号  5位以内数字和字母组合
	TYPE_WORKID,		//工位号ID
	TYPE_FACHE_PZ,		//发车凭证
	TYPE_HKZD,			//航空主单
	
	TYPE_NULL=255
	
}eBARCODE_TYPE;

const U8 *const gc8AreaCode[]=
{
	"010",
	"020",
	"021",
	"022",
	"023",
	"024",
	"025",
	"027",
	"028",
	"029",
	"0471",
	"0351",
	"0311",
	"0431",
	"0451",
	"0551",
	"0531",
	"0571",
	"0791",
	"0591",
	"0731",
	"0371",
	"0898",
	"0771",
	"0851",
	"0871",
	"0931",
	"0951",
	"0971",
	"0991",
	"0891",
	"886",
	"0886",
	"852",
	"0852",
	"853",
	"0853",
};



/*------------------------------------
*函数:hy_IfDigit
*功能:判断字符串是否全是数字
*参数:pStr:待检测的字符串
*返回:HY_OK:成功   HY_ERROR:失败
*------------------------------------*/
int hy_IfDigit(U8 *pStr)
{
	int len,i;
	int ret = HY_OK;
	
	len = strlen(pStr);
	for(i = 0; i < len; i++)
	{
		if(*pStr < '0' || *pStr > '9')
		{
			ret = HY_ERROR;
			break;
		}
		pStr++;
	}
	
	return ret;
}

/*------------------------------------
*函数:hy_IfChar
*功能:判断字符串是否全是字母
*参数:pStr:待检测的字符串
*返回:HY_OK:成功   HY_ERROR:失败
*------------------------------------*/
int hy_IfChar(U8 *pStr)
{
	int len,i;
	int ret = HY_OK;
	U8  ch;

	len = strlen(pStr);
	for(i = 0; i < len; i++)
	{
		ch = *pStr;
		if((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))
		{
			pStr++;
		}
		else
		{
			ret = HY_ERROR;
			break;
		}
	}

	return ret;
}


/*------------------------------------
*函数:hy_IfChar_Digit
*功能:判断字符串是否字母与数字组合
*参数:pStr:待检测的字符串
*返回:HY_OK:成功   HY_ERROR:失败
*------------------------------------*/
int hy_IfChar_Digit(U8 *pStr)
{
	int len,i;
	int ret = HY_OK;
	U8  ch,flag=0;

	len = strlen(pStr);
	for(i = 0; i < len; i++)
	{
		ch = *pStr;
		if(ch >= '0' && ch <= '9')
		{
			pStr++;
		}
		else if((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))
		{
			pStr++;
			flag = 1;
		}
		else
		{
			ret = HY_ERROR;
			flag = 0;
			break;
		}
	}

	if(flag == 1)
	{
		ret = HY_OK;
	}
	else
	{
		ret = HY_ERROR;
	}

	return ret;
}


//成功:HY_OK   失败HY_ERROR
int Op_Comm_CheckCarCode(U8 *pCarCode, int len)
{
	int i, total, ret = HY_ERROR;
	int cmpLen;

	if(len == 9)
	{
		cmpLen = 3;
	}
	else
	{
		cmpLen = 4;
	}
	total = sizeof(gc8AreaCode)/sizeof(U8 *);

	for(i = 0; i < total; i++)
	{
		if(memcmp(pCarCode, gc8AreaCode[i], cmpLen) == 0)
		{
			ret = HY_OK;
			break;
		}
	}


	return ret;
}


/*--------------------------------------
*函数：opCom_CheckBarcodeBase
*功能：根据指定规则检查条码合法性(按基本规则判定)
*	  0x20---0x7F
*参数：pCode:带检查的条码
       codeType:条码种类
*返回：HY_OK:成功    HY_ERROR:失败     0:没有规则
*--------------------------------------*/
int opCom_CheckBarcodeBase(char *pCode, int len)
{
	int i;
	
	for (i=0; i<len; i++)
	{
		if (pCode[i] < ' ' || pCode[i] > '~')
		{
			return HY_ERROR;
		}
	}
	return HY_OK;
}

/*--------------------------------------
*函数：OpCom_CheckBarcode
*功能：根据指定规则检查条码合法性
*参数：pCode:带检查的条码
       codeType:条码种类
*返回：1:成功    -1:失败     0:没有规则
*--------------------------------------*/
int OpCom_CheckBarcode(U8 *pCode, int codeType)
{
	int ret = -1, len, i;
	U8  ch,digit=0,chara=0;
	
	len = strlen(pCode);//条码长度
	if(len == 0)
	{
		return -1;
	}
	//判断条码基本规则(半角可显字符)
	if (opCom_CheckBarcodeBase(pCode, len) == HY_ERROR)
	{
		return -1;
	}
	
	switch(codeType)
	{
	case TYPE_EMPLOYEID://1 员工  中转站只能是 9开头的8位纯数字
		if(hy_IfDigit(pCode) == 0)
		{
			//if(len == 4 || len == 7 || (len == 8 && pCode[0] == '9'))
			if(len == 4 || (len == 8 && pCode[0] == '9'))
			{
				ret = 1;
			}
		}
		break;
	case TYPE_COMPANYID://2 网点 --6位数字
		if(len == 6 && hy_IfDigit(pCode) == 0)
		{
			ret = 1;
		}
		break;
	case TYPE_BARCODE://3 运单 ---13位纯数字
		if(len == 13 && hy_IfDigit(pCode) == 0)
		{
			ret = 1;
		}
		break;
	case TYPE_PACKET://6 大包  --900开头的13位数字
		if(len == 13 && memcmp(pCode, "900", 3)==0 && hy_IfDigit(pCode) == 0)
		{
			ret = 1;
		}
		break;
	case TYPE_CARID://4  车辆  ---9或10位字母和数字组合
		if((len == 9 || len == 10) && hy_IfChar_Digit(pCode) == 0)
		{
			//判断前面的3或4位数字
			if(HY_OK == Op_Comm_CheckCarCode(pCode, len))
			{
				ret = 1;
			}
		}
		break;
	case TYPE_CARLINE://5 车线 --8位数字
		if(len == 8 && hy_IfDigit(pCode) == 0)
		{
			ret = 1;
		}
		break;
	case TYPE_TAG://8 封签  --7开头的13位数字
		if(len == 13 && memcmp(pCode, "7", 1) == 0 && hy_IfDigit(pCode) == 0)
		{
			ret = 1;
		}
		break;
	case TYPE_GEKOU://9 隔口号  两位大写字母GK+三位数字或字母或数字字母组合
		if(len == 5 && memcmp(pCode, "GK", 2) == 0)
		{
			if(hy_IfChar_Digit(pCode) == 0 || hy_IfChar(pCode) == 0)
			{
				ret = 1;
			}
		}
		break;
	case TYPE_WORKID://9 工位号 目前暂定为9-20位的数字与字母组合或纯数字
		if(len >= 9 && len <= 20)
		{
			if(hy_IfDigit(pCode) == 0 || hy_IfChar_Digit(pCode) == 0)
			{
				ret = 1;
			}
		}
		break;
	case TYPE_FACHE_PZ://10  发车凭证 11位纯数字的条码
		if(len == 11 && hy_IfDigit(pCode) == 0 && (memcmp(pCode, "31", 2) == 0||memcmp(pCode, "68", 2) == 0))
		{
			ret = 1;
		}
		break;
	case TYPE_HKZD://11 航空主单 11纯数字条码
		if(len == 11 && hy_IfDigit(pCode) == 0)
		{
			ret = 1;
		}
		break;	
	default:
		break;
	}

	return ret;
}
/*****************************************条码截断*************************************************/

U8 gu8BchId[28];
U8 gu8OpStation[8];

void Shell_GetBchId(U8 *pCode, U8 *pBchid)
{
	int len = strlen(pCode);

	if(memcmp(pCode, gu8BchId, len) == 0)//前13位相同
	{
		if(strlen(gu8BchId) == 18&&memcmp("36",gu8BchId,2)==0||memcmp("37",gu8BchId,2)==0||memcmp("38",gu8BchId,2)==0||memcmp("39",gu8BchId,2)==0)
		{
			memcpy(pBchid, &gu8BchId[13], 3);
			pBchid[3] = 0;
			//hyUsbPrintf("pBchid === %s\r\n",pBchid);
		}
		else
		{
			memcpy(pBchid, &gu8BchId[13], 4);
			pBchid[4] = 0;
		}
		//hyUsbPrintf("Bch id=%s\r\n", pBchid);
	}

	return ;
}
//24位条码才会生成目的站编码
int Shell_CheckStation(U8 *pStation)
{
	int ret=0;

	if(gu8OpStation[0] == 0 || strcmp(pStation, gu8OpStation) == 0)
	{
		ret = 1;
	}

	memset(gu8OpStation, 0, 8);

	return ret;
}


int atoi(const char *str)
{
	long int v = 0;
	int sign = 0;

	while ( *str == ' ')  str++;
	if(*str == '-'||*str == '+')
		sign = *str++;
	while ((unsigned int)(*str - '0') < 10u)
	{
		v = v*10 + *str - '0';
		str++;
	}
	return sign == '-' ? -v:v;
}

U8 Shell_BarCode_Check1(U8 *pCode)
{
	int i,ret = 0;
	U8  buf[2] = {0};
	int check4,check1,sum;

	memcpy(buf, &pCode[16], 2);
	check1 = atoi(buf);
	//hyUsbPrintf("check1 ==== %d\r\n",check1);
	sum = 0;
	for(i=0;i<15;++i)
	{
		sum += (pCode[i]-0x30)*(pCode[i+1]-0x30);
	}
	//hy
	if(check1 == (sum%97))
	{
		ret = 1;
	}
	return ret;
}
//韵达新的18位条码校验  1:成功   0:失败
U8 Shell_BarCode_Check(U8 *pCode)
{
	U8	i,ret = 0;
	U8	buf[6];
	int check4,check1,sum;

	memcpy(buf, &pCode[13], 4);
	buf[4] = 0;
	check4 = atoi(buf);//4位条码校验位

	check1 = pCode[17]-0x30; //扫描枪校验位
	sum = 0;
	for(i = 0; i < 13; i++)
	{
		sum += (pCode[i]-0x30)*check4;
	}
	if(check1 == (sum%10))//扫描枪校验位正确
	{
		ret = 1;
	}

	return ret;
}


/*
现24位运单号规则为：13位运单号+4位验证码+6位集包目的地+1位巴枪校验码。
（注：第24位巴枪校验码只校验前17位条码，原校验规则不变，不校验第18-23位。）
*/
U8 Shell_BarCode_Check24(U8 *pCode)
{
	U8	i,ret = 0;
	U8	buf[6];
	int check4,check1,sum;

	memcpy(buf, &pCode[13], 4);
	buf[4] = 0;
	check4 = atoi(buf);//4位条码校验位

	memset(gu8OpStation, 0, 8);
	memcpy(gu8OpStation, &pCode[17], 6);

	check1 = pCode[23]-0x30; //扫描枪校验位
	sum = 0;
	for(i = 0; i < 13; i++)
	{
		sum += (pCode[i]-0x30)*check4;
	}
	if(check1 == (sum%10))//扫描枪校验位正确
	{
		ret = 1;
	}

	return ret;
}
/*--------------------------------------
*函数：cutCode
*功能：根据指定规则检查条码合法性
*参数：pCode:带检查的条码
       len:条码长度
*返回：0:条码格式正确    1:条码不符合规则
*--------------------------------------*/
int cutCode(U8 *pCode)
{
	int ret = 0,len=0;
	len = strlen(pCode);//条码长度
	if(len > 13 && (len != 18 && len != 24))
	{
		return len;
	}

	//memset(gu8OpStation, 0, 8);

	//如果是18位条码,需要校验
	if(len == 18)
	{
		if(memcmp("36",pCode,2)==0||memcmp("37",pCode,2)==0||memcmp("38",pCode,2)==0||memcmp("39",pCode,2)==0)
		{
			if(0 == Shell_BarCode_Check1(pCode))
			{
				return 2;
			}
			else
			{
				//全部保存到bchid中
				strcpy(gu8BchId, pCode);

				//截断成13位条码
				len = 13;
				pCode[len] = 0;
			}
		}
		else
		{
			if(0 == Shell_BarCode_Check(pCode))
			{
				return 3;
			}
			else
			{
				//全部保存到bchid中
				strcpy(gu8BchId, pCode);

				//截断成13位条码
				len = 13;
				pCode[len] = 0;
			}
		}
	}
	else if(len == 24)
	{
		if(0 == Shell_BarCode_Check24(pCode))
		{
			return 1;
		}
		else
		{
			//全部保存到bchid中
			strcpy(gu8BchId, pCode);

			//截断成13位条码
			len = 13;
			pCode[len] = 0;
		}
	}
	return ret;
}
