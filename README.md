# Gradle AWS cloudformation validation

This plugin provides simple validation logic for AWS Cloudformation templates.

## Add plugin to your build file
```groovy
apply plugin: 'com.thg.opensource.aws.cfnvalidate'
```

## AWS cloudformation validation
To validate a cloudformation template using the AWS SDK:
```groovy

cfnvalidate {
    cfnTemplatesPath = "src/test/resources/templates"
}
```
