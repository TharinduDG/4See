-- Extension description
function descriptor()
    return { title = "4See" ;
             version = "0.1" ;
             author = "Tharindu Galappaththi" ;
             url = "www.esewana.blogspot.com";
             shortdesc = "Lyrics,Subtitles,IMDB info at your finger tips";
             description = "" ;
             capabilities = { }
		    }
end

-- Activation
function activate()
	local patht = vlc.input.item():uri()
	os.execute ("java -jar ./4See.jar")
	
end

-- Deactivation
function deactivate()
    
end



