package com.shaojun.networkdemo.network.service;


import com.shaojun.networkdemo.network.http.Response;
import com.shaojun.networkdemo.network.service.models.MeiZhi;
import com.shaojun.networkdemo.network.service.models.User;
import com.shaojun.networkdemo.network.service.models.request.Login;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Sunflower on 2015/11/4.
 */
public interface APIService {


    /**
     * 获取妹纸图
     *
     * @return
     */
    @GET("http://gank.io/api/data/福利/10/{page}")
    Observable<Response<List<MeiZhi>>> getMeiZhiList(@Path("page") int page);

    @POST("service/user/login/1")
    Observable<Response<User>> login(@Body Login login);
//
//    /**
//     * 根据分类获取帖子列表
//     *
//     * @param id         分类id
//     * @param pageNumber
//     * @param pageSize
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("api/gravida/article/list.json")
//    Observable<Response<List<ArticleListDTO>>> getArticleList(@Field("id") long id,
//                                                              @Field("pageNumber") int pageNumber,
//                                                              @Field("pageSize") int pageSize);
//
//    /**
//     * 检查版本
//     *
//     * @param version
//     * @param type
//     * @param device
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("api/common/version.json")
//    Observable<Response<VersionDto>> checkVersion(@Field("version") String version,
//                                                  @Field("type") String type,
//                                                  @Field("device") String device);
//
//    /**
//     * 获取个人信息
//     *
//     * @param id
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("api/gravida/personal/info.json")
//    Observable<Response<PersonalInfo>> getPersonalInfo(@Field("id") String id);
//
//    /**
//     * 获取个人配置信息
//     *
//     * @param id
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("api/gravida/personal/configs.json")
//    Observable<Response<PersonalConfigs>> getPersonalConfigs(@Field("id") String id);
//
//    @Multipart
//    @POST("api/gravida/personal/update.json")
//    Observable<Response<PersonalInfo>> updatePersonalInfo(@Part("avatar") RequestBody avatar,
//                                                          @Part("id") String id);
//
//    @Multipart
//    @POST("api/gravida/personal/update.json")
//    Observable<Response<PersonalInfo>> updatePersonalInfo(@PartMap Map<String, RequestBody> params);
//
//    /**
//     * 测试用对象作为参数，失败
//     *
//     * @param info
//     * @return
//     */
//    @POST("api/gravida/personal/update.json")
//    Observable<Response<PersonalInfo>> updatePersonalInfo(@Body PersonalInfo info);
//
//
//    @Multipart
//    @POST("api/gravida/product/comment.json")
//    Observable<Response<Object>> commentProduct(@PartMap Map<String, RequestBody> params);
//
//
//    @FormUrlEncoded
//    @POST("api/gravida/remind/flow.json")
//    Observable<Response<List<RemindDTO>>> getNotificationList(@Field("id") String id);


    /**
     * 取消收藏帖子
     * 与{@link #cancelFavorite(String, List)}效果一致
     *
     * @param id
     * @param articleId 传递的为数组
     * @return
     */
    @POST("api/gravida/article/unfavourite.json")
    Observable<Response<Object>> cancelFavoriteWithQuery(@Query("id") String id, @Query("articleId") List<Long> articleId);


    @FormUrlEncoded
    @POST("api/gravida/article/unfavourite.json")
    Observable<Response<Object>> cancelFavorite(@Field("id") String id, @Field("articleId") List<Long> articleId);

}
