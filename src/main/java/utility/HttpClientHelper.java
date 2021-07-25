package utility;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HttpContext;

public class HttpClientHelper {
    public static final CloseableHttpClient createHttpClient(Integer timeout) {
        SSLConnectionSocketFactory sslf = null;
        final SSLContext context;

        try {
            context = SSLContext.getInstance("SSL");
            context.init(null, new TrustManager[] { new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s)
                        throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s)
                        throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }

            }, null);
            sslf = new SSLConnectionSocketFactory(context, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        } catch (NoSuchAlgorithmException e) {

        } catch (KeyManagementException e) {

        }

        final Registry<ConnectionSocketFactory> sockeFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", sslf != null ? sslf : SSLConnectionSocketFactory.getSocketFactory()).build();

        final CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(buildPoolingHttpClientConnectionManager(sockeFactoryRegistry))
                .setConnectionReuseStrategy(DefaultConnectionReuseStrategy.INSTANCE)
                .setKeepAliveStrategy(buildConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(buildRequestConfig(timeout))
                .disableAutomaticRetries()
                .build();

        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                try {
                    httpClient.close();
                } catch (IOException e) {

                }

            }

        });

        return httpClient;
    }

    private static RequestConfig buildRequestConfig(Integer timeout) {
        if (null == timeout || timeout <= 0) {
            timeout = 700;
        }
        return RequestConfig.custom()
            .setSocketTimeout(timeout)
            .setConnectTimeout(timeout)
            .setConnectionRequestTimeout(500)
            .setStaleConnectionCheckEnabled(true)
            .build();
    }

    private static PoolingHttpClientConnectionManager buildPoolingHttpClientConnectionManager(
            Registry<ConnectionSocketFactory> socketFactoryRegistry) {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(
                socketFactoryRegistry);
        connectionManager.setMaxTotal(3000);
        connectionManager.setDefaultMaxPerRoute(60);
        return connectionManager;
    }

    private static ConnectionKeepAliveStrategy buildConnectionKeepAliveStrategy() {
        return new ConnectionKeepAliveStrategy() {

            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                HeaderElementIterator it = new BasicHeaderElementIterator(
                        response.headerIterator(org.apache.http.protocol.HTTP.CONN_KEEP_ALIVE));
                while (it.hasNext()) {
                    HeaderElement he = it.nextElement();
                    String param = he.getName();
                    String value = he.getValue();
                    if (StringUtils.isNumeric(value) && StringUtils.equalsIgnoreCase("timeout", param)) {
                        return Long.parseLong(value) * DateUtils.MILLIS_PER_SECOND;
                    }
                }
                return 1 * DateUtils.MILLIS_PER_MINUTE;
            }

        };
    }

}