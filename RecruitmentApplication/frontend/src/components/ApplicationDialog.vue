<template>
  <v-dialog
    transition="dialog-bottom-transition"
    max-width="600"
    :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          Application
        </v-toolbar>
        <v-form>
          <v-text-field v-model="application.job.name" label="Job" v-bind:readonly=true />
          <v-text-field v-model="application.message" label="Motivation message" v-bind:readonly=true />
          <v-text-field v-model="application.review" label="Review" v-bind:readonly=false />
            <!-- here some button to download CV -->
          <v-btn @click="downloadResume" text>Download resume</v-btn>
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            Review
          </v-btn>
          <v-btn @click="setInvisible">Close</v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "ApplicationDialog",
  props: {
    application: Object,
    opened: Boolean,
    company: Object,
    isRecruiter: Boolean,
  },
  methods: {
    persist() {
        api.applications
          .edit({
            id: this.application.id,
            firstName: this.application.firstName,
            lastName: this.application.lastName,
            phoneNumber: this.application.phoneNumber,
            email: this.application.email,
            address: this.application.address,
            message: this.application.message,
            review: this.application.review,
            resume: this.application.resume,
            job: this.application.job
          })
          .then(() => this.$emit("refresh"));

          //send email with reviewed applicatnon
    },

    setInvisible(){
      this.$emit("refresh")
    },

    downloadResume(){

    }
    
  },
  computed: {
    isNew: function () {
      return !this.job.id;
    },
  },
};
</script>

<style scoped></style>
