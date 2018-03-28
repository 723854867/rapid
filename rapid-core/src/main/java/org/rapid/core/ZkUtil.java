package org.rapid.core;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.rapid.util.Consts;
import org.rapid.util.serialize.SerializeUtil;
import org.rapid.util.serialize.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZkUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(ZkUtil.class);
	
	public static <T> T readJson(ZkClient zkClient, String path, Class<T> clazz) { 
		byte[] buffer = zkClient.readData(path, true);
		if (null == buffer) {
			logger.warn("Zookeeper {} has no data to read!", path);
			return null;
		}
		return SerializeUtil.GSON.fromJson(new String(buffer, Consts.UTF_8), clazz);
	}
	
	public static <T> T read(ZkClient zkClient, String path, Class<T> clazz, Serializer serializer) {
		byte[] buffer = zkClient.readData(path, true);
		if (null == buffer) {
			logger.warn("Zookeeper {} has no data to read!", path);
			return null;
		}
		return serializer.deserial(buffer, clazz);
	}
	
	public static void writeJson(ZkClient zkClient, Object object, String path) {
		byte[] data = SerializeUtil.GSON.toJson(object).getBytes(Consts.UTF_8);
		if (!zkClient.exists(path))
			zkClient.create(path, data, CreateMode.PERSISTENT);
		else
			zkClient.writeData(path, data);
	}
	
	public static void writeJson(ZkClient zkClient, Object object, String path, Serializer deserializer) {
		byte[] data = deserializer.serial(object);
		if (!zkClient.exists(path))
			zkClient.create(path, data, CreateMode.PERSISTENT);
		else
			zkClient.writeData(path, data);
	}
}
