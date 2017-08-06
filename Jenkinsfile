node {

    stage("Main build") {

        checkout scm

        // https://go.cloudbees.com/docs/cloudbees-documentation/cje-user-guide/index.html#docker-workflow
        docker.image('openjdk:8-jdk').inside {
            sh './gradlew clean test'
            sh './gradlew build'
            pwd
            ls -ltra ./build/libs
        }
    }

    stage('Test') {
        docker.image('bash').inside {
            echo "Hello World"
            pwd
            ls -ltra ./build/libs
        }
    }

    stage('Deploy') {
          def newApp = docker.build "daves125125/ci-sample-service:${env.BUILD_TAG}"
          newApp.push()
    }

}
