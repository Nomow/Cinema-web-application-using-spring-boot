

    function ConvertToJsonData(cinemaData) {
        var temp = JSON.parse(cinemaData);
        var jsonObj = [];

        for (i = 0; i < Object.keys(temp).length; ++i) {
            jsonObj[i] = JSON.parse(temp[i]);
        }
        return jsonObj;
    }


    function GetDistance(cinemaData, positionData) {
        var distance = [];
        console.log(cinemaData);
        console.log(positionData);
        for (i = 0; i < cinemaData.length; ++i) {
            var p1 = new google.maps.LatLng(cinemaData[i].latitude, cinemaData[i].longitude);
            var p2 = new google.maps.LatLng(positionData.coords.latitude, positionData.coords.longitude);
            distance[i] = (google.maps.geometry.spherical.computeDistanceBetween(p1, p2) / 1000);
        }
        return distance
    }

    function GetClosestCinemas(cinemaData, positionData) {
        var jsonCinemaData = ConvertToJsonData(cinemaData);
        var distance = GetDistance(jsonCinemaData, positionData)

        // sorts by distance
        for (i = 0; i < distance.length - 1; ++i) {
            for (j = i + 1; j < distance.length; ++j) {
                if (distance[i] > distance[j]) {
                    var temp = distance[i];
                    distance[i] = distance[j];
                    distance[j] = temp;

                    var tempObj = jsonCinemaData[i];
                    jsonCinemaData[i] = jsonCinemaData[j];
                    jsonCinemaData[j] = tempObj;
                }
            }
        }
        console.log("sorted " + distance);
        // adds distance to json object
        for (i = 0; i < distance.length; ++i) {
            if (distance[i] < 1) {
                distance[i] = (Math.round(distance[i] * 1000)) + " meters";
            } else {
                distance[i] = (parseFloat(distance[i]).toFixed(2)) + " kilometers";
            }
            jsonCinemaData[i].distance = distance[i];
        }
        return jsonCinemaData;
    }

// success function
    function geolocationSuccess(position) {
        $.post("/cinemas", {}, function (data, status) {
            if (status == "success") {
                console.log(status);
                console.log(data);
                var cinemaData = GetClosestCinemas(data, position);
                console.log(cinemaData);

                var containers = $('.card');
                for(i = 0; i < containers.length; ++i) {
                    containers.eq(i).find(".cinema-title").text(cinemaData[i].name);
                    containers.eq(i).find(".cinema-address").text(cinemaData[i].address);
                    containers.eq(i).find(".cinema-distance").text(cinemaData[i].distance);
                    containers.eq(i).find(".cinema-city").text(cinemaData[i].city);
                    containers.eq(i).find(".cinema-image").attr("src","/img/cinema/"+ cinemaData[i].name + ".jpg");
                    containers.eq(i).find(".cinema-btn").attr("href", "/cinema/" + cinemaData[i].id);
                }
            } else {
                console.log("error");
            }
        });
    }

// error method
    function geolocationError(positionError) {
        console.warn(`ERROR(${positionError.code}): ${positionError.message}`);
    }


// geolocates user
    function geolocateUser() {
        // If the browser supports the Geolocation API
        if (navigator.geolocation) {
            var positionOptions = {
                enableHighAccuracy: true,
                timeout: 10 * 10000 // 10 seconds
            };
            navigator.geolocation.getCurrentPosition(geolocationSuccess, geolocationError, positionOptions);
        } else {
            geolocationError("failed to get geoleocation");
        }
    }
