package InfluxDataWriter.InfluxWriter;

import org.influxdb.InfluxDB;
import org.influxdb.impl.InfluxDBImpl;

import okhttp3.OkHttpClient;

import org.influxdb.impl.Preconditions;

import java.util.Objects;
	public enum InfluxDBFactory {
		  INSTANCE;

		  /**
		   * Create a connection to a InfluxDB.
		   *
		   * @param url
		   *            the url to connect to.
		   * @return a InfluxDB adapter suitable to access a InfluxDB.
		   */
		  public static InfluxDB connect(final String url) {
		    Preconditions.checkNonEmptyString(url, "url");
		    return new InfluxDBImpl(url, null, null, new OkHttpClient.Builder());
		  }

		  /**
		   * Create a connection to a InfluxDB.
		   *
		   * @param url
		   *            the url to connect to.
		   * @param username
		   *            the username which is used to authorize against the influxDB instance.
		   * @param password
		   *            the password for the username which is used to authorize against the influxDB
		   *            instance.
		   * @return a InfluxDB adapter suitable to access a InfluxDB.
		   */
		  public static InfluxDB connect(final String url, final String username, final String password) {
		    Preconditions.checkNonEmptyString(url, "url");
		    Preconditions.checkNonEmptyString(username, "username");
		    return new InfluxDBImpl(url, username, password, new OkHttpClient.Builder());
		  }

		  /**
		   * Create a connection to a InfluxDB.
		   *
		   * @param url
		   *            the url to connect to.
		   * @param client
		   *            the HTTP client to use
		   * @return a InfluxDB adapter suitable to access a InfluxDB.
		   */
		  public static InfluxDB connect(final String url, final OkHttpClient.Builder client) {
		    Preconditions.checkNonEmptyString(url, "url");
		    Objects.requireNonNull(client, "client");
		    return new InfluxDBImpl(url, null, null, client);
		  }

		  /**
		   * Create a connection to a InfluxDB.
		   *
		   * @param url
		   *            the url to connect to.
		   * @param username
		   *            the username which is used to authorize against the influxDB instance.
		   * @param password
		   *            the password for the username which is used to authorize against the influxDB
		   *            instance.
		   * @param client
		   *            the HTTP client to use
		   * @return a InfluxDB adapter suitable to access a InfluxDB.
		   */
		  public static InfluxDB connect(final String url, final String username, final String password,
		      final OkHttpClient.Builder client) {
		    Preconditions.checkNonEmptyString(url, "url");
		    Preconditions.checkNonEmptyString(username, "username");
		    Objects.requireNonNull(client, "client");
		    return new InfluxDBImpl(url, username, password, client);
		  }
}