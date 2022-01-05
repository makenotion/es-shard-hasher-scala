# es-shard-hasher-scala

Some sample code for running a bunch of workspace ids against a the ES routing hasher if you need that for some reason. 

This script was originally written to find every workspace on shard 142 in our cluster with shards=512, and num_routing_shards=1024 which was empirically discoverd and I have no idea how you're supposed to find the correct value of it

`./sbt run` runs the HelloWorld.scala script once
`./sbt ~run` runs the HelloWorld.scala script in a nodemon-like recompile-rerun loop
