

https://blog.shovonhasan.com/fixing-a-security-vulnerability-in-a-transitive-dependency/

How do you make this go away and still get home on time? If GitHub's automated security fixes feels like admitting defeat or errors out on you, Yarn can make it easy.
If Github's vulnerability report looks like this:

We found a vulnerable dependency in a repository you have security alert access to.

@cpressler	cpressler/spring-react
Known critical severity security vulnerability detected in lodash.mergewith < 4.6.2 defined in yarn.lock.
yarn.lock update suggested: lodash.mergewith ~> 4.6.2.

 
And you don't depend on lodash directly (meaning it's a transitive dependency), an easy solution is to add a "resolutions" field to your package.json with the patched version within its semver range. So in this case our package.json would include these lines:
"resolutions": {
"lodash": "^4.17.13"
},

Example that fixes this
lodash.mergewith ~> 4.6.2.

```json
  "resolutions": {
    "lodash": "^4.17.15",
    "eslint-utils": "^1.4.2",
    "lodash.mergewith": "~>4.6.2"
  }
  
```