package org.rapid.sdk.sina;

import java.io.InputStream;
import java.security.MessageDigest;

public class SinaUtil {
	 /**
     * 计算文件的MD5摘要值 
     * @param file 文件路劲
     * @return 32位的MD5摘要
     */
    public static String getFileMD5(InputStream in) {
      
      MessageDigest digest = null;
      byte buffer[] = new byte[1024];
      int len;
      try {
        digest = MessageDigest.getInstance("MD5");
        while ((len = in.read(buffer, 0, 1024)) != -1) {
          digest.update(buffer, 0, len);
        }
        in.close();
      } catch (Exception e) {
        e.printStackTrace();
        return null;
      }
      String bytes2hex03 = bytes2hex03(digest.digest());
      return bytes2hex03;

    }
    
    public static String bytes2hex03(byte[] bytes)  
    {  
        final String HEX = "0123456789abcdef";  
        StringBuilder sb = new StringBuilder(bytes.length * 2);  
        for (byte b : bytes)  
        {  
            // 取出这个字节的高4位，然后与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数  
            sb.append(HEX.charAt((b >> 4) & 0x0f));  
            // 取出这个字节的低位，与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数  
            sb.append(HEX.charAt(b & 0x0f));  
        }  
        return sb.toString();  
    }  
}
