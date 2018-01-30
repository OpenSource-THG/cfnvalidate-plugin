# Gradle AWS cloudformation validation

This plugin provides simple validation logic for AWS Cloudformation templates.

## Add plugin to your build file


## AWS cloudformation validation
To validate a cloudformation template using the AWS cli application:
```groovy
apply plugin: 'cfnvalidate'

cfnvalidate {
    cfnTemplatesPath = "src/test/resources/templates"
}
```
