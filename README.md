# Facebook Stream Plugin

This is a simple plugin connects your Facebook Live Stream to your Minecraft Server through Streamlabs.
For this to work, you need to use Streamlabs OBS to live stream videos in Facebook.

## Config.yml

```yml

#Change the socket token with your own token
#This can be found in your Streamlabs dashboard
#https://streamlabs.com/dashboard#/settings/api-settings
#In the "API Settings" section, copy the "Your Socket API Token"
socketToken: "yourSocketToken"

#The minecraft username of the player who will receive messages
streamerName: "Streamer"

#The messages to send to the player when the event happened
#Use the placeholder %name% to the get the name of the user who triggered the event
#The placeholder %amount% can be only used in "stars:", to the get the stars amount
#Leave the message empty to disable that event
eventsMessages:
  follow: "%name% followed the page!"
  like: "%name% liked the stream!"
  share: "%name% shared the stream!"
  stars: "%name% sent %amount% stars!"
  support: "%name% became a supporter!"

#Where to send the message
#Set this to true, for serverChat
#Set this to false, for actionBar
messageType: true

#Set this to true to log your events messages to the console
logMessages: true

#Set this to true to play a sound when the event triggered
playSound: true

```