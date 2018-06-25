package demosample.nirav.com.demoproject.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public final class RetrofitSingletonNew {

    private static final String CONTENTYPE = "application/json";
    private static volatile RetrofitSingletonNew instance;

    private RetrofitSingletonNew() {
    }

    public static RetrofitSingletonNew getInstance() {
        if (instance == null) {
            instance = new RetrofitSingletonNew();
        }
        return instance;
    }

    private static OkHttpClient provideOkHttpClient1() {
        return new OkHttpClient.Builder()
                .connectTimeout(80, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    Request original = chain.request();

                    Request request = original.newBuilder()
                            .header("Content-Type", CONTENTYPE)
                            .header("Accept", CONTENTYPE)
                            .method(original.method(), original.body())
                            .build();
                    return chain.proceed(request);


                })
                .build();
    }

    public static OkHttpClient.Builder getUnsafeOkHttpClient() {

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            builder.connectTimeout(80, TimeUnit.SECONDS)
                    .addInterceptor(chain -> {
                        Request original = chain.request();

                        Request request = original.newBuilder()
                                .header("Content-Type", CONTENTYPE)
                                .header("Accept", CONTENTYPE)
                                .method(original.method(), original.body())
                                .build();
                        return chain.proceed(request);
                    });

            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    private static GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create(provideGson());
    }

    private static Gson provideGson() {
        return new GsonBuilder().setLenient().create();
    }

    private static RxJava2CallAdapterFactory provideRxJavaCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    private Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                //.baseUrl("https://api.myjson.com/bins/")
                .baseUrl("https://polycyclic-unions.000webhostapp.com/")
                //.baseUrl("https://api.myjson.com/bins/")
                .addConverterFactory(provideGsonConverterFactory())
                .addCallAdapterFactory(provideRxJavaCallAdapterFactory())
                //    .client(provideOkHttpClient1())
                .client(getUnsafeOkHttpClient().build())
                .build();
    }

    public IDemoWebAPI provideApiService() {
        return provideRetrofit().create(IDemoWebAPI.class);
    }

}
