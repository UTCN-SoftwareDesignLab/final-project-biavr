<template>
  <v-dialog
    transition="dialog-bottom-transition"
    max-width="600"
    :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Job" : "Job" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="job.name" label="Name" v-bind:readonly=!this.isRecruiter />
          <v-text-field v-model="job.description" label="Description" v-bind:readonly=!this.isRecruiter />
          <v-text-field v-model="job.domain" label="Domain" v-bind:readonly=!this.isRecruiter />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist" v-show="isRecruiter" >
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
          <v-btn @click="applyToJob" v-show="!isRecruiter">Apply</v-btn>
          <v-btn @click="deleteJob" v-show="isRecruiter&!isNew" >Delete</v-btn>
          <v-btn @click="setInvisible">Close</v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "JobDialog",
  props: {
    job: Object,
    opened: Boolean,
    company: Object,
    isRecruiter: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.jobs
          .create({
            name: this.job.name,
            description: this.job.description,
            domain: this.job.domain,
          })
          .then((res) => { //this.$emit("refresh")
            console.log("Added job: ", res);
            this.company.jobs.push(res);
            api.companies
              .edit({
                id: this.company.id,
                name: this.company.name,
                description: this.company.description,
                jobs: this.company.jobs,
                users: this.company.users
              })
              .then(() => this.$emit("refresh"))});
            
        //send notification that job was added
        // api.websocket
        // .subscribeToNotification()

      } else {
        api.jobs
          .edit({
            id: this.job.id,
            name: this.job.name,
            description: this.job.description,
            domain: this.job.domain,
          })
          .then(() => this.$emit("refresh"));
      }
    },

    deleteJob(){
        api.jobs
        .delete({
          id: this.job.id,
      })
      .then(() => {
          this.$emit("refresh");
        });
    },

    setInvisible(){
      this.$emit("refresh")
    },

    applyToJob(){
      localStorage.setItem("job",JSON.stringify(this.job));
      this.$router.push({name : "ApplyToJob"});
    },

  },
  computed: {
    isNew: function () {
      return !this.job.id;
    },
  },
};
</script>

<style scoped></style>
