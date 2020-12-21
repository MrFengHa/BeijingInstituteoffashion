<template>
  <div>
    <div style="text-align: left">
      <el-button type="success" @click="to"> 返回</el-button>
    </div>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="展品编号" prop="number">
        <el-input v-model="ruleForm.number"></el-input>
      </el-form-item>
      <el-form-item label="中文名称" prop="cnName">
        <el-input v-model="ruleForm.cnName"></el-input>
      </el-form-item>
      <el-form-item label="英文名称" prop="enName">
        <el-input v-model="ruleForm.enName"></el-input>
      </el-form-item>
      <el-form-item ref="upload_attach_item_iamge" label="展示图片" prop="image" size='small'>
        <el-upload style="float: left"
                   ref="upload_attach"
                   class="upload-demo"
                   action=""
                   multiple
                   accept=".jpg,.png,.jpeg"
                   :limit="1"
                   :on-change="changFileImage"
                   :on-exceed="handleExceed"
                   :on-remove="removeImageFile"
                   :file-list="imageFileList"
                   :auto-upload="false"
        >
          <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
          <div slot="tip" class="el-upload__tip">注:上传图片不能超过1M</div>
        </el-upload>
        <el-progress :percentage="progressPercent" v-show="show_progress"></el-progress>

      </el-form-item>
      <el-form-item ref="upload_attach_item_cnAudio" label="中文配音" prop="cnAudio">
        <el-upload style="float: left"
                   ref="upload_attach"
                   class="upload-demo"
                   action=""
                   multiple
                   accept=".mp3"
                   :limit="1"
                   :on-change="changeCnAudio"
                   :on-exceed="handleExceed"
                   :on-remove="removeCnAudioFile"
                   :file-list="cnAudioFileList"
                   :auto-upload="false"
        >
          <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
          <div slot="tip" class="el-upload__tip">注:上传mp3不能超过1M</div>
        </el-upload>
      </el-form-item>
      <el-form-item ref="upload_attach_item_enAudio" label="英文配音" prop="enAudio">
        <el-upload style="float: left"
                   ref="upload_attach"
                   class="upload-demo"
                   action=""
                   multiple
                   accept=".mp3,"
                   :limit="1"
                   :on-change="changeEnAudio"
                   :on-exceed="handleExceed"
                   :on-remove="removeEnAudioFile"
                   :file-list="enAudioFileList"
                   :auto-upload="false"
        >
          <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
          <div slot="tip" class="el-upload__tip">注:上传mp3不能超过1M</div>
        </el-upload>
      </el-form-item>
      <el-form-item label="中文描述" prop="cnDesc">
        <el-input type="textarea" autosize v-model="ruleForm.cnDesc"></el-input>
      </el-form-item>
      <el-form-item label="英文描述" prop="enDesc">
        <el-input type="textarea" autosize v-model="ruleForm.enDesc"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">{{buttonTitle}}</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import loading from "../../loading";


  export default {

    name: "ExhibitsManager",
    data() {
      //验证密码
      var validateAttachImage = (rule, value, callback) => {

        if (this.imageFileList.length == 0) {
          callback(new Error('请选择展示图片'));
        } else {
          callback();
        }
      };
      //验证密码
      var validateAttachCnAudio = (rule, value, callback) => {

        if (this.cnAudioFileList.length == 0) {
          callback(new Error('请选择中文配音'));
        } else {
          callback();
        }
      };
      //验证密码
      var validateAttachEnAudio = (rule, value, callback) => {

        if (this.enAudioFileList.length == 0) {
          callback(new Error('请选择英文配音'));
        } else {
          callback();
        }
      };
      return {
        ruleForm: {
          number: '',
          cnName: '',
          enName: '',
          cnDesc: '',
          enDesc: ''
        },
        imageFileList: [],
        cnAudioFileList: [],
        enAudioFileList: [],
        progressPercent: 0,
        show_progress: false,

        rules: {
          number: [
            {required: true, message: '请输入展品编号', trigger: 'blur'},
            {min: 3, max: 3, message: '编号为三个字符', trigger: 'blur'}
          ],
          cnName: [
            {required: true, message: '请输入中文名称', trigger: 'blur'},
            {min: 1, max: 50, message: '长度在 0 到 50 个字符', trigger: 'blur'}
          ],
          enName: [
            {required: true, message: '请输入英文名称', trigger: 'blur'},
            {min: 1, max: 100, message: '长度在 0 到 100 个字符', trigger: 'blur'}
          ],
          image: [
            // {  message: '请选择展品图片', trigger: 'blur' },
            {required: true, validator: validateAttachImage}
          ],
          cnAudio: [
            // {required: true, message: '请选择展品中文配音', trigger: 'blur'},
            {required: true, validator: validateAttachCnAudio}
          ],
          enAudio: [
            //{required: true, message: '请选择展品英文配音', trigger: 'blur'},
            {required: true, validator: validateAttachEnAudio}

          ],
          cnDesc: [
            {required: true, message: '请输入中文描述', trigger: 'blur'}
          ],
          enDesc: [
            {required: true, message: '请输入英文描述', trigger: 'blur'}
          ]
        },

       buttonTitle:""
      };
    },
    methods: {
      to() {
        this.$router.push({path: "/exhibits/exhibitsList"});
      },
      changFileImage(file, imageFileList) {
        //选择文件后，给fileList对象赋值
        this.imageFileList = imageFileList
        this.$refs.upload_attach_item_iamge.validate();

      },
      changeCnAudio(file, cnAudioList) {
        //选择文件后，给fileList对象赋值
        this.cnAudioFileList = cnAudioList
        this.$refs.upload_attach_item_cnAudio.validate();

      }, changeEnAudio(file, enAudioList) {
        //选择文件后，给fileList对象赋值
        this.enAudioFileList = enAudioList
        this.$refs.upload_attach_item_enAudio.validate();

      }, removeImageFile(file, fileList) {
        this.imageFileList = fileList
        this.$refs.upload_attach_item;
      },
      removeCnAudioFile(file, fileList) {
        this.cnAudioFileList = fileList
        this.$refs.upload_attach_item;
      },
      removeEnAudioFile(file, fileList) {
        this.enAudioFileList = fileList
        this.$refs.upload_attach_item;
      },
      handleExceed(image, fileList) {
        this.$message.warning(`当前限制最多选择 1 个文件`);
      },
      submitForm(formName) {
        let _this = this;
        this.$refs[formName].validate((valid) => {
          if (valid) {
            let data = new FormData();
            data.append("image", this.imageFileList[0].raw);
            data.append("cnAudio", this.cnAudioFileList[0].raw);
            data.append("enAudio", this.enAudioFileList[0].raw);

            for (let key in this.ruleForm) {
              data.append(key, this.ruleForm[key])
            }
            const _loading = loading(`文件上传中，请稍后...`)

            // this.show_progress = true
            const config = {
              onUploadProgress: progressEvent => {
                // progressEvent.loaded:已上传文件大小
                // progressEvent.total:被上传文件的总大小
                this.progressPercent = Number((progressEvent.loaded / progressEvent.total * 100).toFixed(0))
                _loading.setText('作品上传中，进度：' + this.progressPercent + "%") //更新dialog进度，优化体验
              },
              headers: {
                'Content-Type': 'multipart/form-data'
              }
            }
            this.$http.post("manager/addExhibits", data, config).then((res) => {

              this.imageFileList = [];// 提交完成清空附件列表
              this.cnAudioFileList = [];// 提交完成清空附件列表
              this.enAudioFileList = [];// 提交完成清空附件列表
              _loading.close(); // 关闭加载框
              // this.show_progress = false
              this.progressPercent = 0

              if (res.data.success) {
                this.$message({
                    message: "添加成功",
                    type: 'success',
                  }
                );
                setTimeout(function () {
                  _this.to();
                }, 1500)
              } else {
                this.$message({
                  message: res.data.msg,
                  type: 'error'
                });
              }
            }).catch(function (error) { // 请求失败处理
            });
          } else {
            this.$message({
              message: '请填写完整信息再后提交',
              type: 'error'
            });
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
        this.imageFileList = [];
        this.cnAudioFileList = [];
        this.enAudioFileList = [];
      }
    },
    created() {
      if (this.$route.query.number!=null){
       this.buttonTitle = "更新";
       let _this = this;
       this.$http.get("manager/findById?number="+this.$route.query.number).then((res)=>{
         _this.ruleForm = res.data;
         console.log(res.data)
         _this.$http.get(res.data.imagePath).then((resImage)=>{
           console.log(resImage.data)
         })

       })

      }else{
        this.buttonTitle = "创建";
      }

    }
  }
</script>

<style scoped>
  .myelement {
    text-align: left
  }

  .input_width {
    width: 50%;
    width: 300px;
  }

  fieldset {
    border: 2px solid #DCDFE6;
    text-align: left;
    border-radius: 8px;
  }
</style>
