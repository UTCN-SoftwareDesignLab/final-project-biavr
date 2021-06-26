<template>
<div>
    <h1>Complete your application for</h1>
    <h2>{{this.job.name}} </h2>
    <h3> at </h3>
    <h2> {{this.company.name}} </h2>
    <div>
        <v-card>
        <v-form>
          <v-text-field v-model="application.firstName" label="First name" />
          <v-text-field v-model="application.lastName" label="Last name" />
          <v-text-field v-model="application.email" label="Email" />
          <v-text-field v-model="application.phoneNumber" label="Phone" />
          <v-text-field v-model="application.address" label="Address" />
          <v-text-field v-model="application.message" label="Motivation message" />
        </v-form>
        
      </v-card>
      
    </div>

   <div class="container">
    <div>
        <h2> Upload your resume here </h2>
        <div class = "dropbox">
            <input type="file" id="file" ref="file" required v-on:change="handleFileUpload()" class="input-file" />
            <p v-if="isNotUploaded">Drag your pdf file here<br> or click to browse.</p>
            <p v-if="!isNotUploaded"> {{this.file.name}} </p>
        </div>
      <!-- <button v-on:click="submitFile()">Submit</button> -->
      
    </div>
  </div>

    <div>
        <v-card>
            <v-card-actions class="justify-center">
                <v-btn block color= "primary" rounded large @click="apply">Apply</v-btn>
            </v-card-actions>
        </v-card>
    </div>

    <!-- <div>
      <a11y-dialog id="app-dialog" app-root="#app" dialog-root="#app" @dialog-ref="assignDialogRef">
      <h1 slot="title">Your dialog title</h1>
      <div>
        <p>Your content</p>
      </div>
    </a11y-dialog>
    </div> -->

</div>
</template>

<script>
import api from "../api";
import * as axios from 'axios';
import Vue from 'vue';
import 'v-slim-dialog/dist/v-slim-dialog.css'
import SlimDialog from 'v-slim-dialog'

Vue.use(SlimDialog);

// const user = JSON.parse(localStorage.getItem("user"));
// console.log("User is ",user);
// const token = user.token;

// axios.interceptors.request.use(
//     config => {
//         config.headers.authorization = "Bearer " + token;
//         return config;
//     }
// );

export default {
  name: "JobApplication",
  data(){
      return {
          job: {},
          company : {},
          application : {},
          file: '',
          isNotUploaded: true,
          //dialog: null,
      }
  },
  methods: {
      // showAlert() {
      //   const options = {title: 'Congratulations!', size: 'sm'}
      //   this.$dialogs.alert('You have submitted your application.', options)
      //   .then(res => {
      //     console.log(res) // {ok: true|false|undefined}
      //   })
      // },
      apply() {
        const user = JSON.parse(localStorage.getItem("user"));
        console.log("User is ",user);
        const token = user.token;

        axios.interceptors.request.use(
            config => {
                config.headers.authorization = "Bearer " + token;
                return config;
            }
        );

        let formData = new FormData();

        formData.append("file", this.file);

        return axios.post("http://localhost:8090/api/companies/jobs/applications/upload", formData, {
        headers: {
            "Content-Type": "multipart/form-data"
        }
        })
        .then(res => {
            console.log("RESULT: ", res);
            const app = api.application.create({
                firstName : this.application.firstName,
                lastName : this.application.lastName,
                email : this.application.email,
                phoneNumber : this.application.phoneNumber,
                address : this.application.address,
                message : this.application.message,
                resume : formData,
                job : this.job
            });
            console.log("APPLICATION: ", app);
            //now I want to go to another page
            //this.showAlert();
        });

    },
    
    handleFileUpload(){
        this.isNotUploaded = false;
        this.file = this.$refs.file.files[0];
      }    

  },
  created(){
      this.application = {};
      const jobtoapply = JSON.parse(localStorage.getItem("job"));
      this.job = jobtoapply;
      const company = JSON.parse(localStorage.getItem("company"));
      this.company = company;
      //this.openDialog();
  },
};
</script>

<style lang="scss">
  .dropbox {
    outline: 2px dashed grey; /* the dash box */
    outline-offset: -10px;
    background: lightcyan;
    color: dimgray;
    padding: 10px 10px;
    min-height: 100px; /* minimum height */
    position: relative;
    cursor: pointer;
  }

  .input-file {
    opacity: 0; /* invisible but it's there! */
    width: 90%;
    height: 100px;
    position: absolute;
    cursor: pointer;
  }

  .dropbox:hover {
    background: lightblue; /* when mouse over to the drop zone, change color */
  }

  .dropbox p {
    font-size: 1.2em;
    text-align: center;
    padding: 50px 0;
  }
</style>