define([
    'jquery',
    'backbone',
    'backboneValidation',
    'jquerycookie',
    'jquerySerializeObject',
    'router',
    'Session'
], function ($, Backbone, backboneValidation, jquerycookie, jquerySerializeObject, Router, Session) {

    var pleaseWaitDiv = $('<div class="modal fade" data-keyboard="false" tabindex="-1"><div class="modal-base"><img src="imagenes/spinner.gif" style="display: block; margin: auto;"/></div></div>');
    var callServers = 0;

    var ApplicationModel = Backbone.Model.extend({

        start: function () {
            if ($.cookie('auth_token') === undefined) { // this line is the problem
                $.ajaxSettings.headers = [];
                Session.set('authenticated', false);
                Session.set('roles', '');
            } else {
                var user = JSON.parse($.cookie('auth_token'));
                $.ajaxSetup({
                    headers: {
                        "X-Auth-Token": user.token
                    }
                });
                Session.set('authenticated', true);
                Session.set('username', user.username);
                Session.set('roles', user.roles);
            }

            var router = new Router();
            Backbone.history.start();

            $.ajaxSetup({
                cache: false,
                beforeSend: function () {
                    pleaseWaitDiv.modal('show');
                    callServers = callServers + 1;
                },
                complete: function () {
                    if (callServers <= 1) {
                        pleaseWaitDiv.modal('hide');
                    }
                    callServers = callServers - 1;
                },
                statusCode: {
                    401: function () {
                        // Redirec the to the login page.
                        Session.set('authenticated', false);
                        Session.set('username', '');
                        Session.set('roles', '');
                        $.removeCookie('auth_token')
                        if ($.ajaxSettings.headers["X-Auth-Token"] !== 'undefined') {
                            delete $.ajaxSettings.headers["X-Auth-Token"];
                        }
                        Backbone.history.navigate('login', {trigger: true});

                    },
                    403: function () {
                        // 403 -- Access denied
                        Session.set('authenticated', false);
                        Session.set('username', '');
                        Session.set('roles', '');
                        $.removeCookie('auth_token')
                        if ($.ajaxSettings.headers["X-Auth-Token"] !== 'undefined') {
                            delete $.ajaxSettings.headers["X-Auth-Token"];
                        }
                        Backbone.history.navigate('login', {trigger: true});
                    }
                }
            });

            var router = new Router();

            // Extend the callbacks to work with Bootstrap, as used in this example
            // See: http://thedersen.com/projects/backbone-validation/#configuration/callbacks
            _.extend(Backbone.Validation.callbacks, {
                valid: function (view, attr, selector) {
                    var $el = view.$('[name=' + attr + ']'),
                        $group = $el.closest('.field');

                    $group.removeClass('has-error');
                    $group.find('.help-block').html('').addClass('hidden');
                },
                invalid: function (view, attr, error, selector) {
                    var $el = view.$('[name=' + attr + ']'),
                        $group = $el.closest('.field');

                    $group.addClass('has-error');
                    $group.find('.help-block').html(error).removeClass('hidden');
                }
            });

            $.fn.serializeObject = function () {
                "use strict";
                var a = {}, b = function (b, c) {
                    var d = a[c.name];
                    "undefined" != typeof d && d !== null ? $.isArray(d) ? d.push(c.value) : a[c.name] = [d, c.value] : a[c.name] = c.value
                };
                return $.each(this.serializeArray(), b), a
            };
        }
    });

    return ApplicationModel;
});