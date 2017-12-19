package com.css.common.util;
import java.util.Properties;

public class SeparatorUtil {
//	File.separatorChar ����һ���ַ��ʾ��ǰϵͳĬ�ϵ��ļ���ָ�����Windows��Ϊ"/",unix��Ϊ"/"��
//	File.separator ��ǰ����ͬ�������ָ�����Ϊ�ַ����ͷ��ء�
//	pathSeparatorChar ����һ���ַ��ʾ��ǰϵͳĬ�ϵ�·����ָ�����Windows��Ϊ";",unix��Ϊ":"��
//	File.pathSeparator ��ǰ����ͬ�������ָ�����Ϊ�ַ����ͷ��ء�
	
//	��ͬϵͳƽ̨�µ��зָ���·���ָ���ȳ�����ͬ����
	
//	�зָ�����windows ���� \r\n����Linux������ \n�� ��Mac���� \r �� UNIX ϵͳ����/n
//	·���ָ�����windows���� \ ����LInux���� /  �� UNIX ϵͳ����:
//	�ļ��ָ���winodw��\  linux��/  �� UNIX ϵͳ���ǡ�/����
	 public static void main (String[] args){
	        System.out.println("�зָ�����" + SeparatorUtil.getLineSeparator());
	        System.out.println("·���ָ�����" + SeparatorUtil.getPathSeparator());
	        System.out.println("�ļ��ָ�����" + SeparatorUtil.getFileSeparator());
	 }
	 
    /* system properties to get separators */
    static final Properties PROPERTIES = new Properties(System.getProperties());

    /**
     * ��ȡ��ǰƽ̨�� �ļ��ָ���
     * @return line separator
     */
    public static String getFileSeparator(){
        return PROPERTIES.getProperty("file.separator");
    }
    
    /**
     * ��ȡ��ǰƽ̨�� �зָ���
     * @return line separator
     */
    public static String getLineSeparator(){
        return PROPERTIES.getProperty("line.separator");
    }

    /**
     * ��ȡ��ǰƽ̨�� ·���ָ���
     * @return path separator
     */
    public static String getPathSeparator(){
        return PROPERTIES.getProperty("path.separator");
    }
    
//    �����ܵõ������ԣ�
//
//    java.version
//     Java ����ʱ�����汾
//     
//    java.vendor
//     Java ����ʱ������Ӧ��
//     
//    java.vendor.url
//     Java ��Ӧ�̵� URL
//     
//    java.home
//     Java ��װĿ¼
//     
//    java.vm.specification.version
//     Java �����淶�汾
//     
//    java.vm.specification.vendor
//     Java �����淶��Ӧ��
//     
//    java.vm.specification.name
//     Java �����淶���
//     
//    java.vm.version
//     Java �����ʵ�ְ汾
//     
//    java.vm.vendor
//     Java �����ʵ�ֹ�Ӧ��
//     
//    java.vm.name
//     Java �����ʵ�����
//     
//    java.specification.version
//     Java ����ʱ�����淶�汾
//     
//    java.specification.vendor
//     Java ����ʱ�����淶��Ӧ��
//     
//    java.specification.name
//     Java ����ʱ�����淶���
//     
//    java.class.version
//     Java ���ʽ�汾��
//     
//    java.class.path
//     Java ��·��
//     
//    java.library.path
//     ���ؿ�ʱ������·���б�
//     
//    java.io.tmpdir
//     Ĭ�ϵ���ʱ�ļ�·��
//     
//    java.compiler
//     Ҫʹ�õ� JIT �����������
//     
//    java.ext.dirs
//     һ��������չĿ¼��·��
//     
//    os.name
//     ����ϵͳ�����
//     
//    os.arch
//     ����ϵͳ�ļܹ�
//     
//    os.version
//     ����ϵͳ�İ汾
//     
//    file.separator
//     �ļ��ָ����� UNIX ϵͳ���ǡ�/����
//     
//    path.separator
//     ·���ָ����� UNIX ϵͳ���ǡ�:����
//     
//    line.separator
//     �зָ����� UNIX ϵͳ���ǡ�/n����
//     
//    user.name
//     �û����˻����
//     
//    user.home
//     �û�����Ŀ¼
//     
//    user.dir
//     �û��ĵ�ǰ����Ŀ¼
     

}

