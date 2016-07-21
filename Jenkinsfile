node {
  try{
    slackSend channel: '#general', message: "started ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)", teamDomain: 'trunk-agileworks', token: 'JhXFKEl6cBFoQ4v52BEJw9Mr'
    stage 'checkout project'
    git url: 'https://github.com/agileworks-tw/spring-boot-sample.git'

    stage 'check env'

    sh "mvn -v"
    sh "java -version"

    stage 'test'
    sh "mvn test"

    stage 'package'
    sh "mvn package"

    stage 'preview'
    sh 'make deploy-default'

    stage 'report'
    step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])

    stage 'Artifact'
    step([$class: 'ArtifactArchiver', artifacts: '**/target/*.jar', fingerprint: true])

    try{
      stage 'Approve, go production'
      def url = 'http://localhost:8000/'
      input message: "Does staging at $url look good? ", ok: "Deploy to production"
    }finally{
      sh "ssh jenkins@localhost 'kill `cat deploy/release/run.pid`'"
    }

    stage 'deploy'
    sh 'make deploy-default'

    slackSend channel: '#general', color: 'good', message: "success ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)", teamDomain: 'trunk-agileworks', token: 'JhXFKEl6cBFoQ4v52BEJw9Mr'
  }catch(e){
    slackSend channel: '#general', color: 'danger', message: "fail ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)", teamDomain: 'trunk-agileworks', token: 'JhXFKEl6cBFoQ4v52BEJw9Mr'
    throw e;
  }
}
