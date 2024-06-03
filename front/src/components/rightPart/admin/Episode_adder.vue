<template>
  <div> 
    <el-button size="mini" @click="dialogVisible = true">增加集数</el-button>
    <el-dialog :visible.sync="dialogVisible" :title="'添加 ' + cartoonId + ' 集数'" width="44%" append-to-body>
      <el-form v-model="form" :label-position="'right'" label-width="20%">
        <el-form-item label="添加的集数">
          <el-input-number v-model="form.num" controls-position="right" :min="1" size="small"></el-input-number>
        </el-form-item>
        <el-form-item label="导入方式">
          <el-switch v-model="form.localImport" active-text="本地导入" inactive-text="url导入"></el-switch>
        </el-form-item>
        <el-form-item label="本地导入" v-if="form.localImport">
          <el-upload class="upload-demo" drag action="https://jsonplaceholder.typicode.com/posts/">
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          </el-upload>
        </el-form-item>
        <el-form-item label="url导入" v-else>
          <el-input v-model="form.internetUrl" placeholder="请输入绝对路径"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button size="medium" @click="submitUpload">确认上传</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>

export default {
    props : ["cartoonId"],
    data() {
        return {
            dialogVisible : false,
            form : {
              num : 1,
              localImport : true,
              internetUrl : ""
            }
        }
    },
    methods : {
      submitUpload() {

      }
    }
}
</script>

<style>

</style>