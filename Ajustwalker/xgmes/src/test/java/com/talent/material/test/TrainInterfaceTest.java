package com.talent.material.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TrainInterfaceTest {
	
	@Test
	public void testTrainStatus(){
		Object[] result = new Object[36];
		BufferedReader reader = null;
		Map<String,String> data = new HashMap<String,String>();
		data.put("linecode", "03017");
		data.put("linename", "货场6线");
		data.put("vreceivecode", "HBJC1611139999");
		data.put("vtraincode", "4849508");
		data.put("vtraintype", "C64K");
		data.put("istatus", "0");
		data.put("measstatus", "空车计量");
		result[0] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "01003");
		data.put("linename", "站3线");
		data.put("vreceivecode", "HBJC1611139998");
		data.put("vtraincode", "4608952");
		data.put("vtraintype", "C62BK");
		data.put("istatus", "0");
		data.put("vinvname", "高速线材,ML08Al,φ11mm");
		data.put("vstation", "嘉善");
		data.put("istatus", "0");
		data.put("measstatus", "发出重车计量");
		result[1] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "01003");
		data.put("linename", "站3线");
		data.put("vreceivecode", "HBJC1611139997");
		data.put("vtraincode", "4615636");
		data.put("vtraintype", "C62BK");
		data.put("istatus", "0");
		data.put("vinvname", "高速线材,ML08Al,φ11mm");
		data.put("vstation", "嘉善");
		data.put("istatus", "0");
		data.put("measstatus", "发出重车计量");
		result[2] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "03017");
		data.put("linename", "货场6线");
		data.put("vreceivecode", "HBJC1611139996");
		data.put("vtraincode", "4849508");
		data.put("vtraintype", "C64K");
		data.put("istatus", "0");
		data.put("measstatus", "空车计量");
		result[3] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "01003");
		data.put("linename", "站3线");
		data.put("vreceivecode", "HBJC1611139995");
		data.put("vtraincode", "4608952");
		data.put("vtraintype", "C62BK");
		data.put("istatus", "0");
		data.put("vinvname", "高速线材,ML08Al,φ11mm");
		data.put("vstation", "嘉善");
		data.put("istatus", "0");
		data.put("measstatus", "发出重车计量");
		result[4] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "01003");
		data.put("linename", "站3线");
		data.put("vreceivecode", "HBJC1611139994");
		data.put("vtraincode", "4615636");
		data.put("vtraintype", "C62BK");
		data.put("istatus", "0");
		data.put("vinvname", "高速线材,ML08Al,φ11mm");
		data.put("vstation", "嘉善");
		data.put("istatus", "0");
		data.put("measstatus", "发出重车计量");
		result[5] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "03017");
		data.put("linename", "货场6线");
		data.put("vreceivecode", "HBJC1611139993");
		data.put("vtraincode", "4849508");
		data.put("vtraintype", "C64K");
		data.put("istatus", "0");
		data.put("measstatus", "空车计量");
		result[6] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "01003");
		data.put("linename", "站3线");
		data.put("vreceivecode", "HBJC1611139992");
		data.put("vtraincode", "4608952");
		data.put("vtraintype", "C62BK");
		data.put("istatus", "0");
		data.put("vinvname", "高速线材,ML08Al,φ11mm");
		data.put("vstation", "嘉善");
		data.put("istatus", "0");
		data.put("measstatus", "发出重车计量");
		result[7] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "01003");
		data.put("linename", "站3线");
		data.put("vreceivecode", "HBJC1611139991");
		data.put("vtraincode", "4615636");
		data.put("vtraintype", "C62BK");
		data.put("istatus", "0");
		data.put("vinvname", "高速线材,ML08Al,φ11mm");
		data.put("vstation", "嘉善");
		data.put("istatus", "0");
		data.put("measstatus", "发出重车计量");
		result[8] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "03017");
		data.put("linename", "货场6线");
		data.put("vreceivecode", "HBJC1611139990");
		data.put("vtraincode", "4849508");
		data.put("vtraintype", "C64K");
		data.put("istatus", "0");
		data.put("measstatus", "空车计量");
		result[9] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "01003");
		data.put("linename", "站3线");
		data.put("vreceivecode", "HBJC1611139989");
		data.put("vtraincode", "4608952");
		data.put("vtraintype", "C62BK");
		data.put("istatus", "0");
		data.put("vinvname", "高速线材,ML08Al,φ11mm");
		data.put("vstation", "嘉善");
		data.put("istatus", "0");
		data.put("measstatus", "发出重车计量");
		result[10] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "01003");
		data.put("linename", "站3线");
		data.put("vreceivecode", "HBJC1611139987");
		data.put("vtraincode", "4615636");
		data.put("vtraintype", "C62BK");
		data.put("istatus", "0");
		data.put("vinvname", "高速线材,ML08Al,φ11mm");
		data.put("vstation", "嘉善");
		data.put("istatus", "0");
		data.put("measstatus", "发出重车计量");
		result[11] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "03017");
		data.put("linename", "货场6线");
		data.put("vreceivecode", "HBJC1611139988");
		data.put("vtraincode", "4849508");
		data.put("vtraintype", "C64K");
		data.put("istatus", "0");
		data.put("measstatus", "空车计量");
		result[12] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "01003");
		data.put("linename", "站3线");
		data.put("vreceivecode", "HBJC1611139986");
		data.put("vtraincode", "4608952");
		data.put("vtraintype", "C62BK");
		data.put("istatus", "0");
		data.put("vinvname", "高速线材,ML08Al,φ11mm");
		data.put("vstation", "嘉善");
		data.put("istatus", "0");
		data.put("measstatus", "发出重车计量");
		result[13] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "01003");
		data.put("linename", "站3线");
		data.put("vreceivecode", "HBJC1611139985");
		data.put("vtraincode", "4615636");
		data.put("vtraintype", "C62BK");
		data.put("istatus", "0");
		data.put("vinvname", "高速线材,ML08Al,φ11mm");
		data.put("vstation", "嘉善");
		data.put("istatus", "0");
		data.put("measstatus", "发出重车计量");
		result[14] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "03017");
		data.put("linename", "货场6线");
		data.put("vreceivecode", "HBJC1611139984");
		data.put("vtraincode", "4849508");
		data.put("vtraintype", "C64K");
		data.put("istatus", "0");
		data.put("measstatus", "空车计量");
		result[15] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "01003");
		data.put("linename", "站3线");
		data.put("vreceivecode", "HBJC1611139983");
		data.put("vtraincode", "4608952");
		data.put("vtraintype", "C62BK");
		data.put("istatus", "0");
		data.put("vinvname", "高速线材,ML08Al,φ11mm");
		data.put("vstation", "嘉善");
		data.put("istatus", "0");
		data.put("measstatus", "发出重车计量");
		result[16] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "01003");
		data.put("linename", "站3线");
		data.put("vreceivecode", "HBJC1611139982");
		data.put("vtraincode", "4615636");
		data.put("vtraintype", "C62BK");
		data.put("istatus", "0");
		data.put("vinvname", "高速线材,ML08Al,φ11mm");
		data.put("vstation", "嘉善");
		data.put("istatus", "0");
		data.put("measstatus", "发出重车计量");
		result[17] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "03017");
		data.put("linename", "货场6线");
		data.put("vreceivecode", "HBJC1611139981");
		data.put("vtraincode", "4849508");
		data.put("vtraintype", "C64K");
		data.put("istatus", "0");
		data.put("measstatus", "空车计量");
		result[18] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "01003");
		data.put("linename", "站3线");
		data.put("vreceivecode", "HBJC1611139980");
		data.put("vtraincode", "4608952");
		data.put("vtraintype", "C62BK");
		data.put("istatus", "0");
		data.put("vinvname", "高速线材,ML08Al,φ11mm");
		data.put("vstation", "嘉善");
		data.put("istatus", "0");
		data.put("measstatus", "发出重车计量");
		result[19] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "01003");
		data.put("linename", "站3线");
		data.put("vreceivecode", "HBJC1611139979");
		data.put("vtraincode", "4615636");
		data.put("vtraintype", "C62BK");
		data.put("istatus", "0");
		data.put("vinvname", "高速线材,ML08Al,φ11mm");
		data.put("vstation", "嘉善");
		data.put("istatus", "0");
		data.put("measstatus", "发出重车计量");
		result[20] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "03017");
		data.put("linename", "货场6线");
		data.put("vreceivecode", "HBJC1611139978");
		data.put("vtraincode", "4849508");
		data.put("vtraintype", "C64K");
		data.put("istatus", "0");
		data.put("measstatus", "空车计量");
		result[21] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "01003");
		data.put("linename", "站3线");
		data.put("vreceivecode", "HBJC1611139977");
		data.put("vtraincode", "4608952");
		data.put("vtraintype", "C62BK");
		data.put("istatus", "0");
		data.put("vinvname", "高速线材,ML08Al,φ11mm");
		data.put("vstation", "嘉善");
		data.put("istatus", "0");
		data.put("measstatus", "发出重车计量");
		result[22] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "01003");
		data.put("linename", "站3线");
		data.put("vreceivecode", "HBJC1611139976");
		data.put("vtraincode", "4615636");
		data.put("vtraintype", "C62BK");
		data.put("istatus", "0");
		data.put("vinvname", "高速线材,ML08Al,φ11mm");
		data.put("vstation", "嘉善");
		data.put("istatus", "0");
		data.put("measstatus", "发出重车计量");
		result[23] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "03017");
		data.put("linename", "货场6线");
		data.put("vreceivecode", "HBJC1611139975");
		data.put("vtraincode", "4849508");
		data.put("vtraintype", "C64K");
		data.put("istatus", "0");
		data.put("measstatus", "空车计量");
		result[24] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "01003");
		data.put("linename", "站3线");
		data.put("vreceivecode", "HBJC1611139974");
		data.put("vtraincode", "4608952");
		data.put("vtraintype", "C62BK");
		data.put("istatus", "0");
		data.put("vinvname", "高速线材,ML08Al,φ11mm");
		data.put("vstation", "嘉善");
		data.put("istatus", "0");
		data.put("measstatus", "发出重车计量");
		result[25] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "01003");
		data.put("linename", "站3线");
		data.put("vreceivecode", "HBJC1611139973");
		data.put("vtraincode", "4615636");
		data.put("vtraintype", "C62BK");
		data.put("istatus", "0");
		data.put("vinvname", "高速线材,ML08Al,φ11mm");
		data.put("vstation", "嘉善");
		data.put("istatus", "0");
		data.put("measstatus", "发出重车计量");
		result[26] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "03017");
		data.put("linename", "货场6线");
		data.put("vreceivecode", "HBJC1611139972");
		data.put("vtraincode", "4849508");
		data.put("vtraintype", "C64K");
		data.put("istatus", "0");
		data.put("measstatus", "空车计量");
		result[27] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "01003");
		data.put("linename", "站3线");
		data.put("vreceivecode", "HBJC1611139971");
		data.put("vtraincode", "4608952");
		data.put("vtraintype", "C62BK");
		data.put("istatus", "0");
		data.put("vinvname", "高速线材,ML08Al,φ11mm");
		data.put("vstation", "嘉善");
		data.put("istatus", "0");
		data.put("measstatus", "发出重车计量");
		result[28] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "01003");
		data.put("linename", "站3线");
		data.put("vreceivecode", "HBJC1611139970");
		data.put("vtraincode", "4615636");
		data.put("vtraintype", "C62BK");
		data.put("istatus", "0");
		data.put("vinvname", "高速线材,ML08Al,φ11mm");
		data.put("vstation", "嘉善");
		data.put("istatus", "0");
		data.put("measstatus", "发出重车计量");
		result[29] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "03017");
		data.put("linename", "货场6线");
		data.put("vreceivecode", "HBJC1611139969");
		data.put("vtraincode", "4849508");
		data.put("vtraintype", "C64K");
		data.put("istatus", "0");
		data.put("measstatus", "空车计量");
		result[30] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "01003");
		data.put("linename", "站3线");
		data.put("vreceivecode", "HBJC1611139968");
		data.put("vtraincode", "4608952");
		data.put("vtraintype", "C62BK");
		data.put("istatus", "0");
		data.put("vinvname", "高速线材,ML08Al,φ11mm");
		data.put("vstation", "嘉善");
		data.put("istatus", "0");
		data.put("measstatus", "发出重车计量");
		result[31] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "01003");
		data.put("linename", "站3线");
		data.put("vreceivecode", "HBJC1611139967");
		data.put("vtraincode", "4615636");
		data.put("vtraintype", "C62BK");
		data.put("istatus", "0");
		data.put("vinvname", "高速线材,ML08Al,φ11mm");
		data.put("vstation", "嘉善");
		data.put("istatus", "0");
		data.put("measstatus", "发出重车计量");
		result[32] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "03017");
		data.put("linename", "货场6线");
		data.put("vreceivecode", "HBJC1611139966");
		data.put("vtraincode", "4849508");
		data.put("vtraintype", "C64K");
		data.put("istatus", "0");
		data.put("measstatus", "空车计量");
		result[33] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "01003");
		data.put("linename", "站3线");
		data.put("vreceivecode", "HBJC1611139965");
		data.put("vtraincode", "4608952");
		data.put("vtraintype", "C62BK");
		data.put("istatus", "0");
		data.put("vinvname", "高速线材,ML08Al,φ11mm");
		data.put("vstation", "嘉善");
		data.put("istatus", "0");
		data.put("measstatus", "发出重车计量");
		result[34] = data;
		
		data = new HashMap<String,String>();
		data.put("linecode", "01003");
		data.put("linename", "站3线");
		data.put("vreceivecode", "HBJC1611139964");
		data.put("vtraincode", "4615636");
		data.put("vtraintype", "C62BK");
		data.put("istatus", "0");
		data.put("vinvname", "高速线材,ML08Al,φ11mm");
		data.put("vstation", "嘉善");
		data.put("istatus", "0");
		data.put("measstatus", "发出重车计量");
		result[35] = data;
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			// 创建连接
			URL u = new URL("http://localhost:9080/Logistical/unauth/trainmeasure/status.do");
			HttpURLConnection connection = (HttpURLConnection)u.openConnection();
			connection.setDoOutput(true);  
			connection.setDoInput(true);  
			connection.setRequestMethod("POST");  
			connection.setUseCaches(false);  
			connection.setInstanceFollowRedirects(true);  
			connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式  
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
			connection.setChunkedStreamingMode(5);  
			connection.connect();
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
			out.append(mapper.writeValueAsString(result));
			out.flush();  
			out.close();

			// 读取响应
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"),81920);
			String lines = "";
			StringBuffer sb = new StringBuffer("");
			while ((lines = reader.readLine()) != null){
				sb.append(lines);
			}
			reader.close();
			connection.disconnect();
			System.out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
            try {
                if (reader != null) {
                	reader.close();
                }
            }catch (IOException ex) {
            	ex.printStackTrace();
            }
        }
	}
}
