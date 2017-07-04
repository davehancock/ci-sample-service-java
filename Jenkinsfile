node {

    stage("Main build") {

        checkout scm

        // https://go.cloudbees.com/docs/cloudbees-documentation/cje-user-guide/index.html#docker-workflow
        docker.image('openjdk:8-jdk').inside {
            sh './gradlew clean test'
            sh './gradlew build'
        }
    }


    stage("post build") {

        steps{
            sh 'ls -ltra'

            // Maybe use the plugin for this instead?
            docker --version
            docker build -t test .
        }
    }

}
