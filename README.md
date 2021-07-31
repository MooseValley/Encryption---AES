# Encryption and Decrption with AES

REF: https://stackoverflow.com/questions/13109588/base64-encoding-in-java

Sample code had MANY errors / issues - but all have been fixed by me.

Issues with this algorithm / code / approach:
* The key that does the encryption is the same one that does the decryption.
* No-way to prove that the key (or the application that it is signed with) was
   created by the actual author.  It could have been hacked, cracked, changed, and re-distributed.
* The key must be included somewhere with your code / application.
   It could be obscurified, but the key *must* be in the code, a data file, or
   somewhere hidden in the application somewhere.

**Moose OMalley**
<br>Moose's Software Valley - Established 29-Jul-1996
<br>https://moosevalley.github.io/
