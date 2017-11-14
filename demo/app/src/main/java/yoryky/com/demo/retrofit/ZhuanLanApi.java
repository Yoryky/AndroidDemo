package yoryky.com.demo.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import yoryky.com.demo.entity.ZhuanLanAuthor;

public interface ZhuanLanApi {
    @GET("/api/columns/{user}")
    Call<ZhuanLanAuthor> getAuthor(@Path("user") String user);
}
