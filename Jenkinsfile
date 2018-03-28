node ('docker-enabled') {
    def app

    stage('Clone repository') {
        /* Let's make sure we have the repository cloned to our workspace */

        checkout scm
    }

    stage('Build image') {
        /* This builds the actual image; synonymous to docker build on the command line */
        app = docker.build("qu4rk/hellonode")
    }

    stage('Test image') {
            /* Ideally, we would run a test framework against our image. For this example, we're using a Volkswagen-type approach ;-) */

            app.inside {
                sh 'echo "Tests passed"'
            }
    }

    stage('Push image') {
            /* Finally, we'll push the image with two tags:
             * First, the incremental build number from Jenkins
             * Second, the 'latest' tag.
             * Pushing multiple tags is cheap, as all the layers are reused. */
            docker.withRegistry('https://registry.hub.docker.com', 'dockerhub') {
                app.push("${env.BUILD_NUMBER}")
                app.push("latest")
            }
    }
}

#pipeline {
#    agent {
#        docker {
#            image 'jenkins/ssh-slave'
#            label 'docker-enabled'
#        }
#        // node { label 'docker' }
#    }
#
#    environment {
#        PIPELINE_BUILD_ID = "${BUILD_TAG}"
#        DOCKERHUB_CREDS = credentials("dockerhub")
#        // implicit DOCKERHUB_CREDS_USR
#        // implicit DOCKERHUB_CREDS_PSW
#        // ARTIFACTORY_URI= 'http://192.168.0.20:8071/artifactory'
#    }
#
#    stages {
#        stage('Commit Stage') {
#            steps {
#                echo "BUILD_NUMBER: ${env.BUILD_NUMBER}"
#                echo "BUILD_ID: ${env.BUILD_ID}"
#                echo "NODE_NAME: ${env.NODE_NAME}"
#                echo "JOB_NAME: ${env.JOB_NAME}"
#                echo "BUILD_TAG: ${env.BUILD_TAG}"
#                echo "JENKINS_URL: ${env.JENKINS_URL}"
#                echo "PIPELINE_BUILD_ID: ${env.PIPELINE_BUILD_ID}"
#                echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
#                echo "Artifactory URI ${env.ARTIFACTORY_URI}"
#
#                echo "dockerhub creds: ${env.DOCKERHUB_CREDS}"
#                echo "dockerhub user: ${env.DOCKERHUB_CREDS_USR}"
#                echo "dockerhub pass: ${env.DOCKERHUB_CREDS_PSW}"
#
#                // sh 'echo `ping -c 1 artifactory `'
#                sh './display-env.sh'
#                sh './gradlew clean build buildDockerImage'
#
#
#
#            }
#        }
#        stage('Acceptance Stage') {
#            steps {
#//                input "Proceed?"
#                echo 'TODO Acceptance Stage'
#            }
#        }
#    }
#}
