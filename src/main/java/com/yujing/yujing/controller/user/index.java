package com.yujing.yujing.controller.user;

import com.yujing.yujing.dao.user.Case.Search_case;
import com.yujing.yujing.dao.user.News.Search_news;
import com.yujing.yujing.dao.user.Solution.Search_solution;
import com.yujing.yujing.pojo.*;
import com.yujing.yujing.service.user.Case.CaseService;
import com.yujing.yujing.service.user.News.NewsService;
import com.yujing.yujing.service.user.Solution.SolutionService;
import com.yujing.yujing.service.user.Video.VideoService;
import com.yujing.yujing.service.user.culture.UcultureService;
import com.yujing.yujing.service.user.environmental.UenvironmentalService;
import com.yujing.yujing.service.user.flow.UflowService;
import com.yujing.yujing.service.user.introduce.UintroduceService;
import com.yujing.yujing.service.user.problem.UproblemService;
import com.yujing.yujing.service.user.product.UproductService;
import com.yujing.yujing.service.user.seniority.SeniorityService;
import com.yujing.yujing.service.user.support.UsupportService;
import com.yujing.yujing.tool.Pagebean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Controller
public class index {

    @Autowired
    SeniorityService seniorityService;
    @Autowired
    CaseService caseService;
    @Autowired
    NewsService newsService;
    @Autowired
    SolutionService solutionService;
    @Autowired
    Search_news search_news;
    @Autowired
    Search_case search_case;
    @Autowired
    VideoService videoService;
    @Autowired
    Search_solution search_solution;

    @Value("${web.Allpath}")
    private String Allpath;

    @Autowired
    UsupportService usupportService;

    @Autowired
    UflowService uflowService;

    @Autowired
    UproblemService uproblemService;

    @Autowired
    UintroduceService uintroduceService;

    @Autowired
    UcultureService ucultureService;

    @Autowired
    UenvironmentalService uenvironmentalService;

    @Autowired
    UproductService uproductService;

    @GetMapping(value = "/")
    public String index(){
        return "user/index";
    }

    @GetMapping(value = "soltion/index")
    public String soltion_user(Model model,Integer page,Integer type){
        if (page == null) {
            page = 1;
        }
        if(type==null){
            type=1;
        }
        System.out.println("page:"+page);
        Pagebean<SolutionPojo> selectsolutionlimit = solutionService.selectsolutionlimit(page, type);
        model.addAttribute("sol", selectsolutionlimit);
        model.addAttribute("all", Allpath);
        List<Integer> list = new ArrayList<>();
        int i = 1;
        Integer pagecount = selectsolutionlimit.getPagecount();
        for (; pagecount > 0; pagecount--) {
            list.add(i++);
        }
        model.addAttribute("pages", list);
        model.addAttribute("index", 1);
        System.out.println("查询的解决：" + selectsolutionlimit);
        return "user/soltion/index";
    }
    @GetMapping(value = "soltion/detail/{id}")
    public String soltion_detaile_user(@PathVariable Integer id,Model model){
        System.out.println(id);
        SolutionPojo solutionPojo = search_solution.search_solution_limitbyid(id);
        System.out.println(solutionPojo);
        model.addAttribute("soltion",solutionPojo);
        model.addAttribute("all", Allpath);
        return "user/soltion/detail";
    }

    @GetMapping(value = "physical/detail/{id}")
    public String physical_detaile_user(@PathVariable Integer id,Model model){
        System.out.println(id);
        CasePojo casePojo = search_case.search_case_limitbyid(id);
        System.out.println(casePojo);
        model.addAttribute("physical",casePojo);
        model.addAttribute("all", Allpath);
        return "user/physical/detail";
    }


    @GetMapping(value = "physical/index")
    public String physical_user(Model model,Integer page,Integer type){
        if (page == null) {
            List<Integer> list = search_case.search_type_case();
            page = list.get(0);
        }
        if(type==null){

            type=1;
        }
        System.out.println("page:"+page);
        Pagebean<CasePojo> selectcaselimit = caseService.selectcaselimit(page, type);
        model.addAttribute("ca", selectcaselimit);
        model.addAttribute("all", Allpath);
        List<Integer> list = new ArrayList<>();
        int i = 1;
        Integer pagecount = selectcaselimit.getPagecount();
        for (; pagecount > 0; pagecount--) {
            list.add(i++);
        }
        model.addAttribute("pages", list);
        model.addAttribute("index", 1);
        model.addAttribute("caseone",Allpath+ File.separator+selectcaselimit.getList().get(0).getCase_imgPojos().get(0).getPath());
        System.out.println("链表："+selectcaselimit.getList().get(0).getCase_imgPojos().get(0).getPath());
        System.out.println("查询的案例：" + selectcaselimit);
        return "user/physical/index";
    }

    @GetMapping(value = "services/index")
    public String services_user(Model model){
        Pagebean<Problem> pagebean = uproblemService.uselectproblemlimit();
        Integer pagecount = pagebean.getPagecount();
        int a[] = new int[pagecount];
        for (int i = 1; i<=pagecount;i++){
            a[i-1] = i;
        }
        model.addAttribute("pages",a);
        model.addAttribute("pagebean" ,pagebean );
        Support uselectsupport = usupportService.uselectsupport();
        String a1 = Allpath+ File.separator +uselectsupport.getImg();
        uselectsupport.setImg(a1);
        model.addAttribute("support",uselectsupport);
        List<Flow> uselectflow = uflowService.uselectflow();
        for (Flow flow:uselectflow
        ) {
            String a2 = Allpath+ File.separator +flow.getImg();
            flow.setImg(a2);
        }
        model.addAttribute("flows",uselectflow);
        return "user/services/index";
    }

    @GetMapping(value = "services/detail")
    public String services_detaile_user(){
        return "user/services/detail";
    }

    @GetMapping(value = "profile/index")
    public String profile_user(Model model){
        Culture uselectculture = ucultureService.uselectculture();
        String a = Allpath+ File.separator +uselectculture.getImg();
        uselectculture.setImg(a);
        model.addAttribute("culture" ,uselectculture);
        Introduce uselectintroduce = uintroduceService.uselectintroduce();
        String a1 = Allpath+ File.separator +uselectintroduce.getImg();
        uselectintroduce.setImg(a1);
        model.addAttribute("introduce" ,uselectintroduce);
        Pagebean<Environmental> pagebean = uenvironmentalService.uselectproblemlimit();
        List<Environmental> environmentals = pagebean.getList();
        for (Environmental environmental:environmentals
        ) {
            String a2 = Allpath+ File.separator +environmental.getImg();
            environmental.setImg(a2);
        }
        model.addAttribute("pagebean",pagebean);
        return "user/profile/index";
    }

    @GetMapping(value = "profile/detail/{id}")
    public String profile_detaile_user(@PathVariable Integer id){
        System.out.println(id);
        return "user/services/detail";
    }

    @GetMapping(value = "news/index")
    public String news_user(Model model,Integer page,Integer type){
        if (page == null) {
            page = 1;
        }
        if(type==null){
            type=1;
        }
        System.out.println("page:"+page);
        Pagebean<NewsPojo> selectnewslimit = newsService.selectnewslimit(page, type);
        Model sol = model.addAttribute("news",selectnewslimit);
        model.addAttribute("all", Allpath);
        List<Integer> list = new ArrayList<>();
        int i = 1;
        Integer pagecount = selectnewslimit.getPagecount();
        for (; pagecount > 0; pagecount--) {
            list.add(i++);
        }
        model.addAttribute("pages", list);
        model.addAttribute("index", 1);
        System.out.println("查询的新闻：" + selectnewslimit);
        model.addAttribute("newsone",Allpath+ File.separator+selectnewslimit.getList().get(0).getNews_imgPojos().get(0).getPath());
        System.out.println("链表："+selectnewslimit.getList().get(0).getNews_imgPojos().get(0).getPath());
        return "user/news/index";
    }
    @GetMapping(value = "contact/index")
    public String contact_user(){
        return "user/contact/index";
    }

    @GetMapping(value = "Prouce_center/index")
    public String prouce_user(Model model){
        List<Product_type> uselectproducttype = uproductService.uselectproducttype();
        model.addAttribute("types",uselectproducttype);
        Pagebean<Product> pagebean = uproductService.uselectproductlimit(1);
        List<Product> list1 = pagebean.getList();
        for (Product product:list1
             ) {
            String a = Allpath+ File.separator +product.getImg();
            product.setImg(a);
        }
        model.addAttribute("pagebean",pagebean);
        Integer pagecount = pagebean.getPagecount();
        int a[] = new int[pagecount];
        for (int i = 1; i<=pagecount;i++){
            a[i-1] = i;
        }
        List<Product> list = pagebean.getList();
        model.addAttribute("pages",a);
        return "user/Prouce_center/index";
    }

    @GetMapping(value = "Prouce_center/index/{type}")
    public String prouce_users(Model model ,@PathVariable Integer type){
        List<Product_type> uselectproducttype = uproductService.uselectproducttype();
        model.addAttribute("types",uselectproducttype);
        Pagebean<Product> pagebean = uproductService.uselectproductlimit(type);
        List<Product> list1 = pagebean.getList();
        for (Product product:list1
        ) {
            String a = Allpath+ File.separator +product.getImg();
            product.setImg(a);
        }
        model.addAttribute("pagebean",pagebean);
        Integer pagecount = pagebean.getPagecount();
        int a[] = new int[pagecount];
        for (int i = 1; i<=pagecount;i++){
            a[i-1] = i;
        }
        List<Product> list = pagebean.getList();
        model.addAttribute("pages",a);
        return "user/Prouce_center/index";
    }

    @GetMapping(value = "news/detail/{id}")
    public String news_detaile_user(@PathVariable Integer id,Model model){
        System.out.println(id);
        NewsPojo newsPojo = search_news.search_news_limitById(id);
        Integer integer = search_news.search_count_news();
        System.out.println(newsPojo);
        model.addAttribute("news",newsPojo);
        model.addAttribute("count",integer);
        return "user/news/detail";
    }

    @GetMapping(value = "news/detailhou/{id}")
    public String news_detaile_userhou(@PathVariable Integer id,Model model){
        System.out.println(id);
        Integer integer = search_news.search_count_news();
        System.out.println("xxxxxxxxx"+integer);
        NewsPojo newsPojo = search_news.search_news_limitById(++id);
        if(newsPojo==null){
            while(integer--!=0){
                newsPojo = search_news.search_news_limitById(++id);
                if(newsPojo!=null){
                    break;
                }
            }
        }
        System.out.println(newsPojo);
        model.addAttribute("news",newsPojo);
        model.addAttribute("count",integer);
        return "user/news/detail";
    }

    @GetMapping(value = "profile/culture")
    public String culture_user(Model model){
        Culture uselectculture = ucultureService.uselectculture();
        String a = Allpath+ File.separator +uselectculture.getImg();
        uselectculture.setImg(a);
        model.addAttribute("culture" ,uselectculture);
        return "user/profile/culture";
    }

    @GetMapping(value = "profile/environment")
    public String environment_user(Model model){
        Pagebean<Environmental> pagebean = uenvironmentalService.uselectproblemlimit();
        List<Environmental> environmentals = pagebean.getList();
        for (Environmental environmental:environmentals
        ) {
            String a = Allpath+ File.separator +environmental.getImg();
            environmental.setImg(a);
        }
        Integer pagecount = pagebean.getPagecount();
        int a[] = new int[pagecount];
        for (int i = 1; i<=pagecount;i++){
            a[i-1] = i;
        }
        model.addAttribute("pages",a);
        model.addAttribute("pagebean",pagebean);
        return "user/profile/environment";
    }

    @GetMapping(value = "soltion/detailhou/{id}")
    public String soltion_detaile_userhou(@PathVariable Integer id,Model model){
        System.out.println(id);
        Integer integer = search_solution.search_count_solution();
        SolutionPojo solutionPojo = search_solution.search_solution_limitbyid(++id);
        if(solutionPojo==null){
            while(integer--!=0){
                solutionPojo = search_solution.search_solution_limitbyid(++id);
                if(solutionPojo!=null){
                    break;
                }
            }
        }
        System.out.println(solutionPojo);
        model.addAttribute("soltion",solutionPojo);
        return "user/soltion/detail";
    }

    @GetMapping(value = "soltion/detailqian/{id}")
    public String soltion_detaile_useqian(@PathVariable Integer id,Model model){
        System.out.println(id);
        Integer integer = search_solution.search_count_solution();
        SolutionPojo solutionPojo = search_solution.search_solution_limitbyid(--id);
        if(solutionPojo==null){
            while(integer--!=0){
                solutionPojo = search_solution.search_solution_limitbyid(++id);
                if(solutionPojo!=null){
                    break;
                }
            }
        }
        System.out.println(solutionPojo);
        model.addAttribute("soltion",solutionPojo);
        return "user/soltion/detail";
    }


    @GetMapping(value = "news/detailqian/{id}")
    public String news_detaile_userqian(@PathVariable Integer id,Model model){
        System.out.println(id);
        Integer integer = search_news.search_count_news();
        NewsPojo newsPojo = search_news.search_news_limitById(--id);
        if(newsPojo==null){
            while(integer--!=0){
                newsPojo = search_news.search_news_limitById(--id);
                if(newsPojo!=null){
                    break;
                }
            }
        }
        System.out.println(newsPojo);
        model.addAttribute("news",newsPojo);
        model.addAttribute("count",integer);
        return "user/news/detail";
    }


    @GetMapping(value = "profile/qualification")
    public String qualification_user(Integer page,Model model){
        if (page == null) {
            page = 1;
        }
        Pagebean<SeniorityPojo> selectsenioritylimit = seniorityService.selectsenioritylimit(page);
        model.addAttribute("img", selectsenioritylimit);
        model.addAttribute("all", Allpath);
        List<Integer> list = new ArrayList<>();
        int i = 1;
        Integer pagecount = selectsenioritylimit.getPagecount();
        for (; pagecount > 0; pagecount--) {
            list.add(i++);
        }
        model.addAttribute("pages", list);
        model.addAttribute("index", 1);
        System.out.println("查询的公司资质图片：" + selectsenioritylimit);
        return "user/profile/qualification";
    }

    @GetMapping(value = "profile/video")
    public String video_user(Integer page, Model model){
        if (page == null) {
            page = 1;
        }
        Pagebean<VideoPojo> selectvideolimit = videoService.selectvideolimit(page);
        model.addAttribute("video", selectvideolimit);
        model.addAttribute("all", Allpath);
        List<Integer> list = new ArrayList<>();
        int i = 1;
        Integer pagecount = selectvideolimit.getPagecount();
        for (; pagecount > 0; pagecount--) {
            list.add(i++);
        }
        model.addAttribute("pages", list);
        model.addAttribute("index", 1);
        System.out.println("查询的视频：" + selectvideolimit);
        return "user/profile/video";
    }

    @GetMapping(value = "soltion/type/{type}")
    public String soltion_type(@PathVariable Integer type,Integer page,Model model){
        Pagebean<SolutionPojo> selectsolutionlimit = solutionService.selectsolutionlimit(page, type);
        model.addAttribute("sol", selectsolutionlimit);
        model.addAttribute("all", Allpath);
        List<Integer> list = new ArrayList<>();
        int i = 1;
        Integer pagecount = selectsolutionlimit.getPagecount();
        System.out.println(pagecount);
        for (; pagecount > 0; pagecount--) {
            list.add(i++);
        }
        System.out.println(list.size());
        System.out.println("222222222222222222"+selectsolutionlimit);
        model.addAttribute("pages", list);
        model.addAttribute("type", type);
        model.addAttribute("soltionone",Allpath+ File.separator+selectsolutionlimit.getList().get(0).getNews_solutionPojos().get(0).getPath());
        System.out.println("查询的新闻：" + selectsolutionlimit);
        return "user/soltion/index";
    }

    @GetMapping(value = "news/type/{type}")
    public String news_type(@PathVariable Integer type,Model model){
        Pagebean<NewsPojo> selectnewslimit = newsService.selectnewslimit(1, type);
        model.addAttribute("news", selectnewslimit);
        model.addAttribute("all", Allpath);
        List<Integer> list = new ArrayList<>();
        int i = 1;
        Integer pagecount = selectnewslimit.getPagecount();
        for (; pagecount > 0; pagecount--) {
            list.add(i++);
        }
        System.out.println("222222222222222222"+selectnewslimit);
        model.addAttribute("pages", list);
        model.addAttribute("index", 1);
        model.addAttribute("newsone",Allpath+ File.separator+selectnewslimit.getList().get(0).getNews_imgPojos().get(0).getPath());
        System.out.println("查询的新闻：" + selectnewslimit);
        return "user/news/index";
    }
    @GetMapping(value = "profile/organ")
    public String organ_user(){
        return "user/profile/organ";
    }

    @GetMapping(value = "services/support")
    public String supprot_user(Model model){
        Support uselectsupport = usupportService.uselectsupport();
        if ( null != uselectsupport){
            String a = Allpath+ File.separator +uselectsupport.getImg();
            uselectsupport.setImg(a);
            model.addAttribute("support",uselectsupport);
        }else{
            Support uselectsupport1 =new Support();
            uselectsupport1.setImg("../qianduan/Main/services/img/support.png");
            uselectsupport1.setText("<p>广州宇景水产科技有限公司是从事水产养殖设备、工厂化循环水养殖系统、源水处理系统、海洋馆维生系统设备、水产养殖工程设计、自动化控制设备技术的研发、制造、技术咨询的专业公司。</p>\n" +
                    "\t\t\t\t\t<p>我们的业务范围:<br>\n" +
                    "\t\t\t\t\t\t创新、领先是公司的宗旨。公司自2009年成立以来,在中国水产科学研究院南海水产研究所、黄海水产研究所、珠江水产研究所、中国科学院南海海洋研究所的技术支持下,坚定走自主研发、自主创新之路。<br>\n" +
                    "\t\t\t\t\t\t我们团结协作,共同努力,先后自主创新发明了CL全封闭工厂循环水养殖系统新工艺,发明了循环挂膜技术(CH)、多泵组合节能技术( MPC )和二次泡沫分离二_次臭氧消毒工艺技术(\n" +
                    "\t\t\t\t\t\t2FS+20D).在工厂化循环水系统设备配套方面,发明了旋流式蛋白质分离器、生物机械过滤器、智能型自动投饵机、生态综合处理器等一批获有发明专利的配套产品 ,为全封闭工厂化循环水养殖系统安全、稳定、可靠运行，奠定了坚实的基础。</p>\n" +
                    "\t\t\t\t\t<p>我们的品牌释义:<br>\n" +
                    "\t\t\t\t\t\t我们坚持以信立业、诚信合作、信誉至上的经营理念。<br>\n" +
                    "\t\t\t\t\t\t我们坚持以质取胜、专业品质、不断创新的企业精神。 <br>\n" +
                    "\t\t\t\t\t\t我们坚持提供准确、实用可靠、环保节能的系统方案。<br>\n" +
                    "\t\t\t\t\t\t我们坚持服务周到、产品三包、系统终身维护的承诺。<br>\n" +
                    "\t\t\t\t\t\t创新才能生存、创新才能发展、创新才能领先,我们不会停止脚步,我们还将继续创新。. . . .，</p>");
            model.addAttribute("support",uselectsupport1);
        }
        return "user/services/support";
    }

    @GetMapping(value = "services/process")
    public String process_user(Model model){
        List<Flow> uselectflow = uflowService.uselectflow();
        for (Flow flow:uselectflow
             ) {
            String a = Allpath+ File.separator +flow.getImg();
            flow.setImg(a);
        }
        model.addAttribute("flows",uselectflow);
        return "user/services/process";
    }

    @GetMapping(value = "Prouce_center/detail/{id}")
    public String process_detail_user(Model model, @PathVariable Integer id ){
        List<Product_type> uselectproducttype = uproductService.uselectproducttype();
        model.addAttribute("types",uselectproducttype);
        System.out.println(id);
        Product product = uproductService.uselectproductbyidAll(id);
        model.addAttribute("product",product);
        return "user/Prouce_center/detail";
    }

    @PostMapping(value = "uselectproblemlimitid")
    public List<Problem> uselectproblemlimitid(Integer id){
        Pagebean<Problem> pagebean = uproblemService.uselectproblemlimitid(id);
        List<Problem> list = pagebean.getList();
        return list;
    }

}

