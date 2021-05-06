package cn.stylefeng.guns.cloud.system.modular.ent.controller;

import cn.stylefeng.guns.cloud.libs.scanner.annotation.GetResource;
import cn.stylefeng.guns.cloud.libs.scanner.annotation.PostResource;
import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.libs.validator.stereotype.ParamValidator;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.model.web.response.SuccessResponseData;
import cn.stylefeng.guns.cloud.system.core.util.FtpUtil;
import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntAbnormal;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntAbnormalParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntUserParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntAbnormalResult;
import cn.stylefeng.guns.cloud.system.modular.ent.service.EntAbnormalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

/**
 * 企业信息控制器
 *
 * @author stylefeng
 * @Date 2019-10-10 22:44:17
 */
@RestController
@ApiResource(name = "异常结算记录", path = "/entAbnormal")
@Api(tags = "异常结算记录")
public class EntAbnormalController {

    @Autowired
    private EntAbnormalService entAbnormalService;

    /**
     * 分页查询列表
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    @GetResource(name = "分页查询列表", path = "/queryListPage")
    @ApiOperation(value = "分页查询列表", response = EntAbnormalResult.class)
    public ResponseData queryListPage(EntAbnormalParam param) {
        PageResult<EntAbnormalResult> pageBySpec = entAbnormalService.findPageBySpec(param);
        return ResponseData.success(pageBySpec);
    }

    /**
     * 新增
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    @PostResource(name = "添加", path = "/AbnormalAdd")
    @ApiOperation("新增")
    @ParamValidator
    public ResponseData add(@RequestBody EntAbnormalParam param) throws ParseException {
        entAbnormalService.add(param);
        return ResponseData.success();
    }



    @PostResource(name = "上传文件", path = "/AbnormalUpload", requiredPermission = false)
    public ResponseData upload(@RequestParam("file") MultipartFile file,@RequestParam("id") String id) {
        String fileName = file.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + "/upload/";
        String fPath = filePath + fileName;
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            FtpUtil ftpUtil = new FtpUtil();
            InputStream inputStream = ftpUtil.getFileInputStream(fPath,fileName);
            String pt ="DW/Data Range/";
            ftpUtil.uploadFile(inputStream,pt,fileName);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new SuccessResponseData();
    }

}
