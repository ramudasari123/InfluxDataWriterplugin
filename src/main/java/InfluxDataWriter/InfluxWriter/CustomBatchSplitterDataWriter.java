package InfluxDataWriter.InfluxWriter;

import java.util.concurrent.TimeUnit;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDB.ConsistencyLevel;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.Pong;
import org.influxdb.dto.Query;

public class CustomBatchSplitterDataWriter {
public void influxwriter(String url, String BatchIdName, int batchvalue){
	InfluxDB influxDB = InfluxDBFactory.connect(url, "root", "root");
	System.out.println("connected to DB");
	String dbName = "jmeter";
	influxDB.createDatabase(dbName);
	System.out.println("created DB");
	String rpName = "autogen";
	influxDB.createRetentionPolicy(rpName, dbName, "30d", "30m", 2, true);
	System.out.println("created RP");
	influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
	BatchPoints batchPoints = BatchPoints
					.database(dbName)
					.tag("async", "true")
					.retentionPolicy(rpName)
					.consistency(ConsistencyLevel.ALL)
					.build();
	System.out.println("created BatchPoints");
	Point point1 = Point.measurement("BatchData")
						.time(System.currentTimeMillis(), TimeUnit.MILLISECONDS).addField("BatchId", BatchIdName).addField("ProcessedTime", batchvalue).build();
	System.out.println("created Points");
	influxDB.enableBatch(100, 200, TimeUnit.MILLISECONDS);
	influxDB.setRetentionPolicy("autogen");
	influxDB.setDatabase(dbName);
	System.out.println("Set up DB");
	batchPoints.point(point1);
	influxDB.write(batchPoints);
	System.out.println("Write to DB");
	influxDB.disableBatch();
	influxDB.close();
	//Query query = new Query("SELECT idle FROM BatchID", dbName);
	//influxDB.query(query);
	//influxDB.dropRetentionPolicy(rpName, dbName);
	//influxDB.deleteDatabase(dbName);
}
}
