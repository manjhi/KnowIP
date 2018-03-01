package manjinder.knowip;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Manjinder_Singh on 02-Aug-17.
 */
public interface DataInterface {
    @GET("/")
    Call<IpAddress> getMyIp();
}
