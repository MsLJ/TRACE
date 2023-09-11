var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
var socketIO = require('socket.io');
var xss = require('xss');

var indexRouter = require('./routes/index');
var usersRouter = require('./routes/users');

var app = express();

app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use(function(req, res, next) {
  res.setHeader('Access-Control-Allow-Origin', '*');
  res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, PATCH, DELETE');
  res.setHeader('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept');
  next();
});
app.listen(3000);


app.get("/news.it.get", function(req, res) {
    var mongojs = require("mongojs");
    var db = mongojs("195.168.9.65/xe");
    var collection = db.collection("Trace_News_it");
    
    const page = parseInt(req.query.page) || 1; 
    const pageSize = 10; 
    
    collection.find()
        .sort({ pubdate: -1 })
        .skip((page - 1) * pageSize)
        .limit(pageSize)
        .toArray(function(err, result) {
            if (err) {
                res.status(500).send(err);
            } else {
                res.send(result);
            }
        });
});
app.get("/news.culture.get", function(req, res) {
    var mongojs = require("mongojs");
    var db = mongojs("195.168.9.65/xe");
    var collection = db.collection("Trace_News_culture");
    
    const page = parseInt(req.query.page) || 1; 
    const pageSize = 10; 
    
    collection.find()
        .sort({ pubdate: -1 })
        .skip((page - 1) * pageSize)
        .limit(pageSize)
        .toArray(function(err, result) {
            if (err) {
                res.status(500).send(err);
            } else {
                res.send(result);
            }
        });
});

app.get("/news.world.get", function(req, res) {
    var mongojs = require("mongojs");
    var db = mongojs("195.168.9.65/xe");
    var collection = db.collection("Trace_News_world");
    
    const page = parseInt(req.query.page) || 1; 
    const pageSize = 10; 
    
    collection.find()
        .sort({ pubdate: -1 })
        .skip((page - 1) * pageSize)
        .limit(pageSize)
        .toArray(function(err, result) {
            if (err) {
                res.status(500).send(err);
            } else {
                res.send(result);
            }
        });
});
var io = socketIO();
io.listen(1234);

io.sockets.on("connection", function(socket) {
  socket.on("clientMsg", function(m) {
		const sanitizedInput = xss(m.txt);
		 const escapedMsg = escapeHtml(sanitizedInput);
    io.sockets.emit("serverMsg", { nn: m.nn,
            txt: escapedMsg});
  });
});

function escapeHtml(unsafe) {
    return unsafe.replace(/</g, "&lt;").replace(/>/g, "&gt;");
}





app.use(function(req, res, next) {
  next(createError(404));
});


app.use(function(err, req, res, next) {

  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};


  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;

