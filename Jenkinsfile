pipeline {
    agent {
//        docker {
//            image 'jenkins/ssh-slave'
//            label 'docker-enabled'
//            args '-v /var/run/docker.sock:/var/run/docker.sock -v $HOME/jenkins_remote_root/.gradle/wrapper/dists:/root/.gradle/wrapper/dists -v $HOME/.m2:/root/.m2'
//        }
        node { label 'docker-enabled' }
    }

    environment {
        PIPELINE_BUILD_ID = "${GIT_BRANCH}-${BUILD_NUMBER}"
        DOCKERHUB_CREDS = credentials("dockerhub")
        // implicit DOCKERHUB_CREDS_USR
        // implicit DOCKERHUB_CREDS_PSW
        // ARTIFACTORY_URI= 'http://192.168.0.20:8071/artifactory'
    }

    stages {
        stage('Commit Stage') {
            steps {
                echo "BUILD_NUMBER: ${env.BUILD_NUMBER}"
                echo "BUILD_ID: ${env.BUILD_ID}"
                echo "NODE_NAME: ${env.NODE_NAME}"
                echo "JOB_NAME: ${env.JOB_NAME}"
                echo "BUILD_TAG: ${env.BUILD_TAG}"
                echo "JENKINS_URL: ${env.JENKINS_URL}"
                echo "PIPELINE_BUILD_ID: ${env.PIPELINE_BUILD_ID}"
                echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
                echo "Artifactory URI ${env.ARTIFACTORY_URI}"

                echo "dockerhub creds: ${env.DOCKERHUB_CREDS}"
                echo "dockerhub user: ${env.DOCKERHUB_CREDS_USR}"
                echo "dockerhub pass: ${env.DOCKERHUB_CREDS_PSW}"

                sh './display-env.sh'
                sh './gradlew clean build buildDockerImage'
                sh '''\
                docker login -u $DOCKERHUB_CREDS_USR -p $DOCKERHUB_CREDS_PSW
                docker push qu4rk/thesis-eurekaservice:$PIPELINE_BUILD_ID
                '''



            }
        }
        stage('Acceptance Stage') {
            steps {
//                input "Proceed?"
                echo 'TODO Acceptance Stage'
            }
        }
    }

//    post {
//        always {
//             junit 'build/reports/**/*.xml'
//        }
//    }
}
