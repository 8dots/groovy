#!/usr/bin/env groovy
import hudson.model.*
jenkins = Jenkins.instance

def call() {
  node('jenkins-build-slave') {
    stage('checkout') {
      checkout scm
    }
    def p = pipelineCfg()
    if (p.runTests == true) {
      stage('Unit-Testing') {
        container('jenkins-build-slave') {
          // withCredentials([
            sh "echo debug"
            //   string(credentialsId: 'ACRUSER', variable: 'ACRUSER'), 
            //   string(credentialsId: 'ACRPASS', variable: 'ACRPASS'), 
            //   string(credentialsId: 'ACR_ENDPOINT', variable: 'ACR_ENDPOINT'), 
            //   string(credentialsId: 'BS_CONFIG', variable: 'BS_CONFIG')
            //   ]) {    
            // checkout scm
            // GitShortCommit = sh(returnStdout: true, script: "git log -n 1 --pretty=format:'%h'").trim()
            // sh "docker login $ACR_ENDPOINT -u $ACRUSER -p $ACRPASS"
            // sh "docker build -t $ACR_ENDPOINT/${p.repoName}:${GitShortCommit} ."
            // sh "docker run --env-file $BS_CONFIG $ACR_ENDPOINT/${p.repoName}:${GitShortCommit} npm test"
          // }
        }
      }
    }
    if (env.BRANCH_NAME == 'master' && p.deployUponTestSuccess == true) {
      stage('Deploy') {
          sh "echo deploy"
      }
    }
  }
}