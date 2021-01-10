import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

object ExtraQueryInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        val url = originalUrl.newBuilder()
            .addQueryParameter("raw_json", "1")
            .build()

        val requestBuilder = originalRequest.newBuilder().url(url)
        val request = requestBuilder.build()

        return chain.proceed(request)
    }
}
