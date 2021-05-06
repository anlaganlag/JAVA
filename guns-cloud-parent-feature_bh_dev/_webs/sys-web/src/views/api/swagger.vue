<template>
    <iframe :src="iframeUrl" width="100%" height="100%" frameborder="0"></iframe>
</template>

<script>
  export default {
    name: 'swagger',
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
        title: 'swagger文档',
        isShowVisible: true,
        iframeUrl: '',
      }
    },
    created() {
      this.iframeUrl = `${process.env.VUE_APP_SWAGGER_URL}`
      console.log(this.iframeUrl);
    }
  }
</script>

<style scoped>

</style>
