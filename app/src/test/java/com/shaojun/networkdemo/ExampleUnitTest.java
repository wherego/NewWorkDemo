package com.shaojun.networkdemo;

import com.google.gson.Gson;
import com.shaojun.networkdemo.network.http.Response;
import com.shaojun.networkdemo.network.http.RetrofitManager;
import com.shaojun.networkdemo.network.service.TestApiService;
import com.shaojun.networkdemo.network.service.models.MeiZhi;
import com.shaojun.networkdemo.network.service.models.User;
import com.shaojun.networkdemo.network.service.models.request.Login;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import retrofit2.Call;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testMeiZhi() {
        try {
            System.out.println("=====================");
            System.out.println("testMeiZhi start");

            TestApiService apiService = RetrofitManager.getInstance().getTestApiService();
            Call<Response<List<MeiZhi>>> call = apiService.testGetMeiZhiList(1);
            Response<List<MeiZhi>> response = call.execute().body();

            if (response.isSuccess()) {
                List<MeiZhi> meiZhis = response.results;
                System.out.println("成功:" + new Gson().toJson(meiZhis));
                Assert.assertNotNull(meiZhis);
            } else {
                String errorMsg = response.errorMsg;
                System.out.println("失败:" + errorMsg);
                Assert.assertNotNull(null);
            }

        } catch (Exception e) {
            System.out.println("异常:" + e.getMessage());
            Assert.assertNotNull(null);

        } finally {
            System.out.println("testMeiZhi  end");
            System.out.println("=====================");
        }

    }

    @Test
    public void testLogin() {
        try {
            System.out.println("=====================");
            System.out.println("testLogin start");


            Login login=new Login();
            login.accountName = "15618338695";
            login.password = "lfs123456";


//            RequestBody usernameBody = RequestBody.create(
//                    MediaType.parse("multipart/form-data"), username);
//            RequestBody passwordkBody = RequestBody.create(
//                    MediaType.parse("multipart/form-data"), password);


            TestApiService apiService = RetrofitManager.getInstance().getTestApiService();
            Call<Response<User>> call = apiService.login(login);
            Response<User> response = call.execute().body();

            if (response.isSuccess()) {
                User user = response.results;
                System.out.println("成功:" + new Gson().toJson(user));
                Assert.assertNotNull(user);
            } else {
                String errorMsg = response.errorMsg;
                System.out.println("失败:" + errorMsg);
                Assert.assertNotNull(null);
            }

        } catch (Exception e) {
            System.out.println("异常:" + e.getMessage());
            Assert.assertNotNull(null);

        } finally {
            System.out.println("testLogin  end");
            System.out.println("=====================");
        }

    }

}