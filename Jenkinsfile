node {

    env.GRADLE_USER_HOME = "/foo"

    def dockerBuild
    stage('Package') {

        def gitVars = checkout scm

        docker.image('openjdk:8-jdk').inside {
            sh './gradlew compileJava'
            sh './gradlew assemble'
        }

        dockerBuild = docker.build "daves125125/ci-sample-service:${gitVars["GIT_COMMIT"]}"
    }

    stage('Test') {
        docker.image('openjdk:8-jdk').inside {
            sh './gradlew test'
        }
    }

    stage('Deploy') {
        docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-login') {
            dockerBuild.push()
            dockerBuild.push 'latest'
        }
    }

}
