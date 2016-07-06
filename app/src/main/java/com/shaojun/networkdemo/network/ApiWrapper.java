package com.shaojun.networkdemo.network;

import com.shaojun.networkdemo.network.http.Response;
import com.shaojun.networkdemo.network.http.RetrofitManager;
import com.shaojun.networkdemo.network.service.models.MeiZhi;
import com.shaojun.networkdemo.network.service.models.User;
import com.shaojun.networkdemo.network.service.models.request.Login;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * api包装类
 */
public class ApiWrapper {

    /**
     * 获取妹纸图
     *
     * @return
     */
    public Observable<List<MeiZhi>> getMeiZhiList(int page) {
        return RetrofitManager.getInstance().getApiService().getMeiZhiList(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<Response<List<MeiZhi>>, Observable<List<MeiZhi>>>() {
                    @Override
                    public Observable<List<MeiZhi>> call(final Response<List<MeiZhi>> listResponse) {
                        return Observable.create(new Observable.OnSubscribe<List<MeiZhi>>() {
                            @Override
                            public void call(Subscriber<? super List<MeiZhi>> subscriber) {
                                if (!subscriber.isUnsubscribed()) {
                                    subscriber.onNext(listResponse.results);
                                }

                                if (!subscriber.isUnsubscribed()) {
                                    subscriber.onCompleted();
                                }
                            }
                        });
                    }
                });
    }


    /**
     * 登陆
     *
     * @return UserInfo
     */
    public Observable<User> login(String accountName, String password) {
        Login login = new Login();
        login.accountName = accountName;
        login.password = password;
        return RetrofitManager.getInstance().getApiService().login(login)
                .compose(this.<User>applySchedulers());
    }


//    /**
//     * 获取帖子分类
//     *
//     * @return
//     */
//    public Observable<List<Object>> getArticleCategory() {
//        return getApiService().getArticleCategory()
////                .subscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .flatMap(new Func1<Response<List<ArticleCategory>>, Observable<List<ArticleCategory>>>() {
////                    @Override
////                    public Observable<List<ArticleCategory>> call(Response<List<ArticleCategory>> listResponse) {
////                        return flatResponse(listResponse);
////                    }
////                })
//
//                .compose(this.<List<Object>>applySchedulers());
//    }


//    /**
//     * 上传单个文件
//     *
//     * @param path
//     * @return
//     */
//    public Observable<PersonalInfo> updatePersonalInfo(String path) {
//        File file = new File(path);
//        RequestBody id = RequestBody.create(MediaType.parse("text/plain"), "139");
//        //直接传递文件
////        RequestBody avatar = RequestBody.create(MediaType.parse("image/*"), file);
//        //传递byte[]
//        Bitmap bitmap = ClippingPicture.decodeBitmapSd(path);
//        RequestBody avatar = RequestBody.create(MediaType.parse("image/*"), ClippingPicture.bitmapToBytes(bitmap));
//        Map<String, RequestBody> params = new HashMap<>();
//        params.put("id", id);
//        params.put("avatar\"; filename=\"" + file.getName() + "", avatar);
//        return getApiService().updatePersonalInfo(params)
////                .subscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .flatMap(new Func1<Response<PersonalInfo>, Observable<PersonalInfo>>() {
////                    @Override
////                    public Observable<PersonalInfo> call(Response<PersonalInfo> personalInfoResponse) {
////                        return flatResponse(personalInfoResponse);
////                    }
////                })
//                .compose(this.<PersonalInfo>applySchedulers())
//                ;
//
//    }


//    /**
//     * 同时传递多个文件
//     *
//     * @param orderId
//     * @param productId
//     * @param content
//     * @param paths
//     * @return
//     */
//    public Observable<Object> commentProduct(long orderId, long productId, String content, List<String> paths) {
//        RequestBody id = RequestBody.create(MediaType.parse("text/plain"), "166");
//        RequestBody orderIdBody = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(orderId));
//        RequestBody productIdBody = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(productId));
////        RequestBody contentBody = RequestBody.create(MediaType.parse("text/plain"), content);
//        RequestBody contentBody = createRequestBody(content);
//        Map<String, RequestBody> params = new HashMap<>();
//        params.put("id", id);
//        params.put("orderId", orderIdBody);
//        params.put("productId", productIdBody);
//        params.put("content", contentBody);
//        for (String image : paths) {
//            File file = new File(image);
//            RequestBody images = RequestBody.create(MediaType.parse("image/*"), file);
//            //key值中为images
//            params.put("images\"; filename=\"" + file.getName() + "", images);
//        }
//        return getApiService().commentProduct(params)
////                .subscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .flatMap(new Func1<Response<Object>, Observable<?>>() {
////                    @Override
////                    public Observable<?> call(Response<Object> objectResponse) {
////                        return flatResponse(objectResponse);
////                    }
////                })
//                .compose(this.<Object>applySchedulers())
//                ;
//
//    }


    /**
     * 传递数组
     *
     * @param articleId
     * @return
     */
    public Observable<Object> cancelFavorite(List<Long> articleId) {
        return RetrofitManager.getInstance().getApiService().cancelFavorite("139", articleId)
                .compose(this.<Object>applySchedulers());

    }

///////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * http://www.jianshu.com/p/e9e03194199e
     * <p>
     * Transformer实际上就是一个Func1<Observable<T>, Observable<R>>，
     * 换言之就是：可以通过它将一种类型的Observable转换成另一种类型的Observable，
     * 和调用一系列的内联操作符是一模一样的。
     *
     * @param <T>
     * @return
     */
    protected <T> Observable.Transformer<Response<T>, T> applySchedulers() {
        return (Observable.Transformer<Response<T>, T>) transformer;
    }

    final Observable.Transformer transformer = new Observable.Transformer() {
        @Override
        public Object call(Object observable) {
            return ((Observable) observable)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .flatMap(new Func1() {
                        @Override
                        public Object call(Object response) {
                            return flatResponse((Response<Object>) response);
                        }
                    });
        }
    };

    /**
     * 对网络接口返回的Response进行分割操作
     *
     * @param response
     * @param <T>
     * @return
     */
    public <T> Observable<T> flatResponse(final Response<T> response) {
        return Observable.create(new Observable.OnSubscribe<T>() {

            @Override
            public void call(Subscriber<? super T> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(response.result);
                }

                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }
            }
        });
    }
}
