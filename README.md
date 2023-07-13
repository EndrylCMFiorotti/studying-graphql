# GraphQL

Initial configuration to run GraphQL in a new project

## How to implements GraphQL in Android

Initially we need to have the URL of the API that we will implement in the app. I used the "countries@current" api as an example, which is using this URL:
```
https://countries.trevorblades.com/graphql
```

## Installing

Knowing which API you will use and having the base URL in hand, then we must configure the gradle file at the app level of the application.

1th - Within the scope of the plugins insert this code snippet:
```gradle
id('com.apollographql.apollo3').version("3.8.2")
```

2th - Just below the scope of the plugin insert more this code snippet:
```gradle
apollo {
  service("service") {
    packageName.set("com.example")
  }
}
```

3th - Now within the scope of the dependencies will insert this implementation:
```gradle
def apollo_graphql_version = '3.8.2'
implementation "com.apollographql.apollo3:apollo-runtime:$apollo_graphql_version"
```

4th - Then go to files > settings > Plugins and search for "graphql" and download the GraphQL plugin.

After you perform the sync will show a sync error saying that the GraphQL folder could not be found:
```
No schema file found in:
src/main/graphql
```

You must open the IDE terminal and run the following code:
```bash
./gradlew :app:downloadApolloSchema --endpoint='https://countries.trevorblades.com/graphql' --schema=app/src/main/graphql/com/example/schema.graphqls
```

Appearing Build Success in the terminal, if you open the app/src/main directory, you will see the GraphQL directory with the schema.graphqls there.

With that now it's up to you to create your queries!

Hope that helped!

## The video that helped me create this repository
[Video made by Philipp Lackner](https://www.youtube.com/watch?v=ME3LH2bib3g&t=988s&ab_channel=PhilippLackner)

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.
