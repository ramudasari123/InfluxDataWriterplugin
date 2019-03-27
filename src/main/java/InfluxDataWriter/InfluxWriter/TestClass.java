package InfluxDataWriter.InfluxWriter;

public class TestClass {
	public static void main(String[] args){
		CustomBatchSplitterDataWriter writer  = new CustomBatchSplitterDataWriter();
		writer.influxwriter("http://localhost:8086","24534545-b566-431a-8110-7eae414d6e1a", 2334);
		
		//CustomBatchDataWriter writer = new CustomBatchDataWriter();
		//writer.influxwriter("http://localhost:8086","c8ad7c32-a9fd-4f3c-92a0-55bfcb4a6d1b", "MessageId","79a747fb-d54b-4422-9d80-c9e984ec7626",161);
	  }
}
