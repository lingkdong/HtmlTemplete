/**
 * Created with IntelliJ IDEA.
 * User: DT254
 * Date: 16-5-20
 * Time: 下午12:36
 * To change this template use File | Settings | File Templates.
 */
$(function(){
    $(".main-li-div-img").on("click",function(){
        var url =$(this).attr("data-url");
        window.open(url,"_blank");
    });
})

