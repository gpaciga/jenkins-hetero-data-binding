# Hetero Data Binding

This is a small example of a plugin, trying to get data binding to work with a hetero list.

The idea is that the configuration of the plugin should take a list of some number of items that extend the `Phone` class. Users get a dropdown list of the different types of `Phones`, and can add as many of each type as they like. In the real world, each item takes a different configuration. Using a `<f:hetero-list>` works in this example, but I think to take advantage of data binding, we need to use a `<f:repeatableHeteroProperty>`, which does not seem to work (or, more likely, I don't know how to use it properly).

The real goal here is that the configuration can be understood by JCasC, and seen here: http://0.0.0.0:8080/jenkins/configuration-as-code/viewExport

The `call` methods and the `EventListener` class are just there to give the plugin something to do; they are not important.
