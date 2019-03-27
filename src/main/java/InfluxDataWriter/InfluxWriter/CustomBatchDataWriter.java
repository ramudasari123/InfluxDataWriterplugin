package InfluxDataWriter.InfluxWriter;

import java.util.concurrent.TimeUnit;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDB.ConsistencyLevel;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;

public class CustomBatchDataWriter {

	public void influxwriter(String url, String BatchIdName, String MesssageIdentifier,String IdentifierValue, int Timetaken){
		InfluxDB influxDB = InfluxDBFactory.connect(url, "admin", "admin");
		String dbName = "jmeter";
		influxDB.createDatabase(dbName);
		String rpName = "autogen";
		influxDB.createRetentionPolicy(rpName, dbName, "30d", "30m", 2, true);
		influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
		BatchPoints batchPoints = BatchPoints
						.database(dbName)
						.tag("async", "true")
						.retentionPolicy(rpName)
						.consistency(ConsistencyLevel.ALL)
						.build();
		Point point1 = Point.measurement("BatchData")
							.time(System.currentTimeMillis(), TimeUnit.MILLISECONDS).addField("BatchId", BatchIdName).addField(MesssageIdentifier, IdentifierValue).addField("ProcessedTime", Timetaken).build();
		influxDB.enableBatch(100, 200, TimeUnit.MILLISECONDS);
		influxDB.setRetentionPolicy("autogen");
		influxDB.setDatabase(dbName);
		
		batchPoints.point(point1);
		influxDB.write(batchPoints);
		influxDB.disableBatch();
		influxDB.close();
		//Query query = new Query("SELECT idle FROM BatchID", dbName);
		//influxDB.query(query);
		//influxDB.dropRetentionPolicy(rpName, dbName);
		//influxDB.deleteDatabase(dbName);
	}
}
