<template>
  <el-card>
    <iframe :src="iframeUrl" width="100%" height="600px" frameborder="0"></iframe>
  </el-card>
</template>

<script>
  export default {
    name: 'druid',
    props: {
      width: {
        type: String,
        default: '80vw'
      },
      modelId: {
        type: String,
      },
    },
    data() {
      return {
        title: 'druid',
        isShowVisible: true,
        iframeUrl: '',
      }
    },
    created() {
      this.iframeUrl = `${process.env.VUE_APP_DRUID_URL}`
    }
  }
</script>

<style scoped>

</style>
