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
          <v-text-field v-model="job.name" label="Job" v-bind:readonly=true />
          <v-text-field v-model="application.message" label="Motivation message" v-bind:readonly=true />
          <v-text-field v-model="application.review" label="Review" v-bind:readonly=false />
            <!-- here some button to download CV -->
          <v-btn @click="downloadResume" color = "primary" text> Download resume </v-btn>
        </v-form>
        <v-card-actions>
          <v-btn @click="persist"> Add Review </v-btn>
          <v-btn @click="setInvisible"> Close </v-btn>
        </v-card-actions>
        <v-card-actions>
          <v-btn @click="calendarEvent"> Schedule interview </v-btn>
        </v-card-actions>
        <v-form v-show=calendarVisible>
          <v-text-field v-model="eventDetails.description" label="Description" />
          <v-text-field v-model="eventDetails.location" label="Location" />
          <v-text-field v-model="eventDetails.startDate" label="Start date (ex. 2021-07-02T17:00:00-10:00)" />
          <v-text-field v-model="eventDetails.endDate" label="End date (ex. 2021-07-02T17:00:00-10:00)" />
        </v-form>
        <v-card-actions v-show=calendarVisible>
          <v-btn @click="scheduleEvent" > Done </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";
import * as axios from 'axios';

export default {
  name: "ApplicationDialog",
  props: {
    application: Object,
    job: Object,
    opened: Boolean,
    eventDetails: Object,
  },
  data(){
    return {
      calendarVisible: false,
    }
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
          .then(() => {
              api.sms.sendMessage({
                message: this.application.review,
                number: this.application.phoneNumber,
              })
              .then(() => {
                console.log("Check your phone")
                this.$emit("refresh");
              })
              
            });

          //send email with reviewed applicatnon
    },

    setInvisible(){
      this.$emit("refresh")
    },

    downloadResume(){
      const user = JSON.parse(localStorage.getItem("user"));
      //console.log("User is ",user);
      const token = user.token;

      axios.interceptors.request.use(
          config => {
              config.headers.authorization = "Bearer " + token;
              return config;
          }
      );

      axios({
          url: 'http://localhost:8090/api/companies/jobs/applications/files/' + this.application.resume,
          method: 'GET',
          responseType: 'blob',
        }).then((response) => {
              var fileURL = window.URL.createObjectURL(new Blob([response.data]));
              var fileLink = document.createElement('a');

              fileLink.href = fileURL;
              fileLink.setAttribute('download', this.application.firstName+'_'+this.application.lastName+'.pdf');
              document.body.appendChild(fileLink);

              fileLink.click();
        });
    },

    calendarEvent(){
      this.calendarVisible=true;
    },

    scheduleEvent(){
      this.calendarVisible=false;
      api.calendar
        .create({
          description: this.eventDetails.description,
          location: this.eventDetails.location,
          startDate: this.eventDetails.startDate,
          endDate: this.eventDetails.endDate,
          attendeeEmail: this.application.email,
        })
        .then(() => this.$emit("refresh"));
    }
    
  },
};
</script>

<style scoped></style>
