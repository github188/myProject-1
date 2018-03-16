(function(root, factory) {
    if (typeof module !== "undefined" && module.exports) {
        module.exports = factory(require("jquery")(root))
    } else {
        if (typeof define === "function" && define.amd) {
            define("bootstrap-dialog", ["jquery"],
            function($) {
                return factory($)
            })
        } else {
            root.BootstrapDialog = factory(root.jQuery)
        }
    }
} (this,
function($) {
    var BootstrapDialog = function(options) {
        this.defaultOptions = $.extend(true, {
            id: BootstrapDialog.newGuid(),
            buttons: [],
            data: {},
            onshow: null,
            onshown: null,
            onhide: null,
            onhidden: null
        },
        BootstrapDialog.defaultOptions);
        this.indexedButtons = {};
        this.registeredButtonHotkeys = {};
        this.draggableData = {
            isMouseDown: false,
            mouseOffset: {}
        };
        this.realized = false;
        this.opened = false;
        this.initOptions(options);
        this.holdThisInstance()
    };
    BootstrapDialog.NAMESPACE = "bootstrap-dialog";
    BootstrapDialog.TYPE_DEFAULT = "type-default";
    BootstrapDialog.TYPE_INFO = "type-info";
    BootstrapDialog.TYPE_PRIMARY = "type-primary";
    BootstrapDialog.TYPE_SUCCESS = "type-success";
    BootstrapDialog.TYPE_WARNING = "type-warning";
    BootstrapDialog.TYPE_DANGER = "type-danger";
    BootstrapDialog.DEFAULT_TEXTS = {};
    BootstrapDialog.DEFAULT_TEXTS[BootstrapDialog.TYPE_DEFAULT] = "Information";
    BootstrapDialog.DEFAULT_TEXTS[BootstrapDialog.TYPE_INFO] = "Information";
    BootstrapDialog.DEFAULT_TEXTS[BootstrapDialog.TYPE_PRIMARY] = "Information";
    BootstrapDialog.DEFAULT_TEXTS[BootstrapDialog.TYPE_SUCCESS] = "Success";
    BootstrapDialog.DEFAULT_TEXTS[BootstrapDialog.TYPE_WARNING] = "Warning";
    BootstrapDialog.DEFAULT_TEXTS[BootstrapDialog.TYPE_DANGER] = "Danger";
    BootstrapDialog.SIZE_NORMAL = "size-normal";
    BootstrapDialog.SIZE_LARGE = "size-large";
    BootstrapDialog.BUTTON_SIZES = {};
    BootstrapDialog.BUTTON_SIZES[BootstrapDialog.SIZE_NORMAL] = "";
    BootstrapDialog.BUTTON_SIZES[BootstrapDialog.SIZE_LARGE] = "btn-lg";
    BootstrapDialog.ICON_SPINNER = "glyphicon glyphicon-asterisk";
    BootstrapDialog.ZINDEX_BACKDROP = 1040;
    BootstrapDialog.ZINDEX_MODAL = 1050;
    BootstrapDialog.defaultOptions = {
        type: BootstrapDialog.TYPE_PRIMARY,
        size: BootstrapDialog.SIZE_NORMAL,
        cssClass: "",
        title: null,
        message: null,
        nl2br: true,
        closable: true,
        closeByBackdrop: true,
        closeByKeyboard: true,
        spinicon: BootstrapDialog.ICON_SPINNER,
        autodestroy: true,
        draggable: false,
        animate: true
    };
    BootstrapDialog.configDefaultOptions = function(options) {
        BootstrapDialog.defaultOptions = $.extend(true, BootstrapDialog.defaultOptions, options)
    };
    BootstrapDialog.dialogs = {};
    BootstrapDialog.openAll = function() {
        $.each(BootstrapDialog.dialogs,
        function(id, dialogInstance) {
            dialogInstance.open()
        })
    };
    BootstrapDialog.closeAll = function() {
        $.each(BootstrapDialog.dialogs,
        function(id, dialogInstance) {
            dialogInstance.close()
        })
    };
    BootstrapDialog.moveFocus = function() {
        var lastDialogInstance = null;
        $.each(BootstrapDialog.dialogs,
        function(id, dialogInstance) {
            lastDialogInstance = dialogInstance
        });
        if (lastDialogInstance !== null && lastDialogInstance.isRealized()) {
            lastDialogInstance.getModal().focus()
        }
    };
    BootstrapDialog.showScrollbar = function() {
        var lastDialogInstance = null;
        $.each(BootstrapDialog.dialogs,
        function(id, dialogInstance) {
            lastDialogInstance = dialogInstance
        });
        if (lastDialogInstance !== null && lastDialogInstance.isRealized() && lastDialogInstance.isOpened()) {
            var bsModal = lastDialogInstance.getModal().data("bs.modal");
            bsModal.checkScrollbar();
            $("body").addClass("modal-open");
            bsModal.setScrollbar()
        }
    };
    BootstrapDialog.prototype = {
        constructor: BootstrapDialog,
        initOptions: function(options) {
            this.options = $.extend(true, this.defaultOptions, options);
            return this
        },
        holdThisInstance: function() {
            BootstrapDialog.dialogs[this.getId()] = this;
            return this
        },
        initModalStuff: function() {
            this.setModal(this.createModal()).setModalDialog(this.createModalDialog()).setModalContent(this.createModalContent()).setModalHeader(this.createModalHeader()).setModalBody(this.createModalBody()).setModalFooter(this.createModalFooter());
            this.getModal().append(this.getModalDialog());
            this.getModalDialog().append(this.getModalContent());
            this.getModalContent().append(this.getModalHeader()).append(this.getModalBody()).append(this.getModalFooter());
            return this
        },
        createModal: function() {
            var $modal = $('<div class="modal" tabindex="-1"></div>');
            $modal.prop("id", this.getId());
            return $modal
        },
        getModal: function() {
            return this.$modal
        },
        setModal: function($modal) {
            this.$modal = $modal;
            return this
        },
        createModalDialog: function() {
            return $('<div class="modal-dialog"></div>')
        },
        getModalDialog: function() {
            return this.$modalDialog
        },
        setModalDialog: function($modalDialog) {
            this.$modalDialog = $modalDialog;
            return this
        },
        createModalContent: function() {
            return $('<div class="modal-content"></div>')
        },
        getModalContent: function() {
            return this.$modalContent
        },
        setModalContent: function($modalContent) {
            this.$modalContent = $modalContent;
            return this
        },
        createModalHeader: function() {
            return $('<div class="modal-header"></div>')
        },
        getModalHeader: function() {
            return this.$modalHeader
        },
        setModalHeader: function($modalHeader) {
            this.$modalHeader = $modalHeader;
            return this
        },
        createModalBody: function() {
            return $('<div class="modal-body"></div>')
        },
        getModalBody: function() {
            return this.$modalBody
        },
        setModalBody: function($modalBody) {
            this.$modalBody = $modalBody;
            return this
        },
        createModalFooter: function() {
            return $('<div class="modal-footer"></div>')
        },
        getModalFooter: function() {
            return this.$modalFooter
        },
        setModalFooter: function($modalFooter) {
            this.$modalFooter = $modalFooter;
            return this
        },
        createDynamicContent: function(rawContent) {
            var content = null;
            if (typeof rawContent === "function") {
                content = rawContent.call(rawContent, this)
            } else {
                content = rawContent
            }
            if (typeof content === "string") {
                content = this.formatStringContent(content)
            }
            return content
        },
        formatStringContent: function(content) {
            if (this.options.nl2br) {
                return content.replace(/\r\n/g, "<br />").replace(/[\r\n]/g, "<br />")
            }
            return content
        },
        setData: function(key, value) {
            this.options.data[key] = value;
            return this
        },
        getData: function(key) {
            return this.options.data[key]
        },
        setId: function(id) {
            this.options.id = id;
            return this
        },
        getId: function() {
            return this.options.id
        },
        getType: function() {
            return this.options.type
        },
        setType: function(type) {
            this.options.type = type;
            return this
        },
        getSize: function() {
            return this.options.size
        },
        setSize: function(size) {
            this.options.size = size;
            return this
        },
        getCssClass: function() {
            return this.options.cssClass
        },
        setCssClass: function(cssClass) {
            this.options.cssClass = cssClass;
            return this
        },
        getTitle: function() {
            return this.options.title
        },
        setTitle: function(title) {
            this.options.title = title;
            this.updateTitle();
            return this
        },
        updateTitle: function() {
            if (this.isRealized()) {
                var title = this.getTitle() !== null ? this.createDynamicContent(this.getTitle()) : this.getDefaultText();
                this.getModalHeader().find("." + this.getNamespace("title")).html("").append(title)
            }
            return this
        },
        getMessage: function() {
            return this.options.message
        },
        setMessage: function(message) {
            this.options.message = message;
            this.updateMessage();
            return this
        },
        updateMessage: function() {
            if (this.isRealized()) {
                var message = this.createDynamicContent(this.getMessage());
                this.getModalBody().find("." + this.getNamespace("message")).html("").append(message)
            }
            return this
        },
        isClosable: function() {
            return this.options.closable
        },
        setClosable: function(closable) {
            this.options.closable = closable;
            this.updateClosable();
            return this
        },
        setCloseByBackdrop: function(closeByBackdrop) {
            this.options.closeByBackdrop = closeByBackdrop;
            return this
        },
        canCloseByBackdrop: function() {
            return this.options.closeByBackdrop
        },
        setCloseByKeyboard: function(closeByKeyboard) {
            this.options.closeByKeyboard = closeByKeyboard;
            return this
        },
        canCloseByKeyboard: function() {
            return this.options.closeByKeyboard
        },
        isAnimate: function() {
            return this.options.animate
        },
        setAnimate: function(animate) {
            this.options.animate = animate;
            return this
        },
        updateAnimate: function() {
            if (this.isRealized()) {
                this.getModal().toggleClass("fade", this.isAnimate())
            }
            return this
        },
        getSpinicon: function() {
            return this.options.spinicon
        },
        setSpinicon: function(spinicon) {
            this.options.spinicon = spinicon;
            return this
        },
        addButton: function(button) {
            this.options.buttons.push(button);
            return this
        },
        addButtons: function(buttons) {
            var that = this;
            $.each(buttons,
            function(index, button) {
                that.addButton(button)
            });
            return this
        },
        getButtons: function() {
            return this.options.buttons
        },
        setButtons: function(buttons) {
            this.options.buttons = buttons;
            this.updateButtons();
            return this
        },
        getButton: function(id) {
            if (typeof this.indexedButtons[id] !== "undefined") {
                return this.indexedButtons[id]
            }
            return null
        },
        getButtonSize: function() {
            if (typeof BootstrapDialog.BUTTON_SIZES[this.getSize()] !== "undefined") {
                return BootstrapDialog.BUTTON_SIZES[this.getSize()]
            }
            return ""
        },
        updateButtons: function() {
            if (this.isRealized()) {
                if (this.getButtons().length === 0) {
                    this.getModalFooter().hide()
                } else {
                    this.getModalFooter().find("." + this.getNamespace("footer")).html("").append(this.createFooterButtons())
                }
            }
            return this
        },
        isAutodestroy: function() {
            return this.options.autodestroy
        },
        setAutodestroy: function(autodestroy) {
            this.options.autodestroy = autodestroy
        },
        getDefaultText: function() {
            return BootstrapDialog.DEFAULT_TEXTS[this.getType()]
        },
        getNamespace: function(name) {
            return BootstrapDialog.NAMESPACE + "-" + name
        },
        createHeaderContent: function() {
            var $container = $("<div></div>");
            $container.addClass(this.getNamespace("header"));
            $container.append(this.createTitleContent());
            $container.prepend(this.createCloseButton());
            return $container
        },
        createTitleContent: function() {
            var $title = $("<div></div>");
            $title.addClass(this.getNamespace("title"));
            return $title
        },
        createCloseButton: function() {
            var $container = $("<div></div>");
            $container.addClass(this.getNamespace("close-button"));
            var $icon = $('<button class="close">&times;</button>');
            $container.append($icon);
            $container.on("click", {
                dialog: this
            },
            function(event) {
                event.data.dialog.close()
            });
            return $container
        },
        createBodyContent: function() {
            var $container = $("<div></div>");
            $container.addClass(this.getNamespace("body"));
            $container.append(this.createMessageContent());
            return $container
        },
        createMessageContent: function() {
            var $message = $("<div></div>");
            $message.addClass(this.getNamespace("message"));
            return $message
        },
        createFooterContent: function() {
            var $container = $("<div></div>");
            $container.addClass(this.getNamespace("footer"));
            return $container
        },
        createFooterButtons: function() {
            var that = this;
            var $container = $("<div></div>");
            $container.addClass(this.getNamespace("footer-buttons"));
            this.indexedButtons = {};
            $.each(this.options.buttons,
            function(index, button) {
                if (!button.id) {
                    button.id = BootstrapDialog.newGuid()
                }
                var $button = that.createButton(button);
                that.indexedButtons[button.id] = $button;
                $container.append($button)
            });
            return $container
        },
        createButton: function(button) {
            var $button = $('<button class="btn"></button>');
            $button.addClass(this.getButtonSize());
            $button.prop("id", button.id);
            if (typeof button.icon !== "undefined" && $.trim(button.icon) !== "") {
                $button.append(this.createButtonIcon(button.icon))
            }
            if (typeof button.label !== "undefined") {
                $button.append(button.label)
            }
            if (typeof button.cssClass !== "undefined" && $.trim(button.cssClass) !== "") {
                $button.addClass(button.cssClass)
            } else {
                $button.addClass("btn-default")
            }
            if (typeof button.hotkey !== "undefined") {
                this.registeredButtonHotkeys[button.hotkey] = $button
            }
            $button.on("click", {
                dialog: this,
                $button: $button,
                button: button
            },
            function(event) {
                var dialog = event.data.dialog;
                var $button = event.data.$button;
                var button = event.data.button;
                if (typeof button.action === "function") {
                    button.action.call($button, dialog)
                }
                if (button.autospin) {
                    $button.toggleSpin(true)
                }
            });
            this.enhanceButton($button);
            return $button
        },
        enhanceButton: function($button) {
            $button.dialog = this;
            $button.toggleEnable = function(enable) {
                var $this = this;
                if (typeof enable !== "undefined") {
                    $this.prop("disabled", !enable).toggleClass("disabled", !enable)
                } else {
                    $this.prop("disabled", !$this.prop("disabled"))
                }
                return $this
            };
            $button.enable = function() {
                var $this = this;
                $this.toggleEnable(true);
                return $this
            };
            $button.disable = function() {
                var $this = this;
                $this.toggleEnable(false);
                return $this
            };
            $button.toggleSpin = function(spin) {
                var $this = this;
                var dialog = $this.dialog;
                var $icon = $this.find("." + dialog.getNamespace("button-icon"));
                if (typeof spin === "undefined") {
                    spin = !($button.find(".icon-spin").length > 0)
                }
                if (spin) {
                    $icon.hide();
                    $button.prepend(dialog.createButtonIcon(dialog.getSpinicon()).addClass("icon-spin"))
                } else {
                    $icon.show();
                    $button.find(".icon-spin").remove()
                }
                return $this
            };
            $button.spin = function() {
                var $this = this;
                $this.toggleSpin(true);
                return $this
            };
            $button.stopSpin = function() {
                var $this = this;
                $this.toggleSpin(false);
                return $this
            };
            return this
        },
        createButtonIcon: function(icon) {
            var $icon = $("<span></span>");
            $icon.addClass(this.getNamespace("button-icon")).addClass(icon);
            return $icon
        },
        enableButtons: function(enable) {
            $.each(this.indexedButtons,
            function(id, $button) {
                $button.toggleEnable(enable)
            });
            return this
        },
        updateClosable: function() {
            if (this.isRealized()) {
                this.getModalHeader().find("." + this.getNamespace("close-button")).toggle(this.isClosable())
            }
            return this
        },
        onShow: function(onshow) {
            this.options.onshow = onshow;
            return this
        },
        onShown: function(onshown) {
            this.options.onshown = onshown;
            return this
        },
        onHide: function(onhide) {
            this.options.onhide = onhide;
            return this
        },
        onHidden: function(onhidden) {
            this.options.onhidden = onhidden;
            return this
        },
        isRealized: function() {
            return this.realized
        },
        setRealized: function(realized) {
            this.realized = realized;
            return this
        },
        isOpened: function() {
            return this.opened
        },
        setOpened: function(opened) {
            this.opened = opened;
            return this
        },
        handleModalEvents: function() {
            this.getModal().on("show.bs.modal", {
                dialog: this
            },
            function(event) {
                var dialog = event.data.dialog;
                if (typeof dialog.options.onshow === "function") {
                    return dialog.options.onshow(dialog)
                }
            });
            this.getModal().on("shown.bs.modal", {
                dialog: this
            },
            function(event) {
                var dialog = event.data.dialog;
                typeof dialog.options.onshown === "function" && dialog.options.onshown(dialog)
            });
            this.getModal().on("hide.bs.modal", {
                dialog: this
            },
            function(event) {
                var dialog = event.data.dialog;
                if (typeof dialog.options.onhide === "function") {
                    return dialog.options.onhide(dialog)
                }
            });
            this.getModal().on("hidden.bs.modal", {
                dialog: this
            },
            function(event) {
                var dialog = event.data.dialog;
                typeof dialog.options.onhidden === "function" && dialog.options.onhidden(dialog);
                dialog.isAutodestroy() && $(this).remove()
            });
            this.getModal().on("mouseup", {
                dialog: this
            },
            function(event) {
                event.target === this && event.data.dialog.isClosable() && event.data.dialog.canCloseByBackdrop() && event.data.dialog.close()
            });
            this.getModal().on("keyup", {
                dialog: this
            },
            function(event) {
                event.which === 27 && event.data.dialog.isClosable() && event.data.dialog.canCloseByKeyboard() && event.data.dialog.close()
            });
            this.getModal().on("keyup", {
                dialog: this
            },
            function(event) {
                var dialog = event.data.dialog;
                if (typeof dialog.registeredButtonHotkeys[event.which] !== "undefined") {
                    var $button = $(dialog.registeredButtonHotkeys[event.which]); ! $button.prop("disabled") && $button.focus().trigger("click")
                }
            });
            return this
        },
        makeModalDraggable: function() {
            if (this.options.draggable) {
                this.getModalHeader().addClass(this.getNamespace("draggable")).on("mousedown", {
                    dialog: this
                },
                function(event) {
                    var dialog = event.data.dialog;
                    dialog.draggableData.isMouseDown = true;
                    var dialogOffset = dialog.getModalContent().offset();
                    dialog.draggableData.mouseOffset = {
                        top: event.clientY - dialogOffset.top,
                        left: event.clientX - dialogOffset.left
                    }
                });
                this.getModal().on("mouseup mouseleave", {
                    dialog: this
                },
                function(event) {
                    event.data.dialog.draggableData.isMouseDown = false
                });
                $("body").on("mousemove", {
                    dialog: this
                },
                function(event) {
                    var dialog = event.data.dialog;
                    if (!dialog.draggableData.isMouseDown) {
                        return
                    }
                    dialog.getModalContent().offset({
                        top: event.clientY - dialog.draggableData.mouseOffset.top,
                        left: event.clientX - dialog.draggableData.mouseOffset.left
                    })
                })
            }
            return this
        },
        updateZIndex: function() {
            var dialogCount = 0;
            $.each(BootstrapDialog.dialogs,
            function(dialogId, dialogInstance) {
                dialogCount++
            });
            if (dialogCount > 1) {
                var $modal = this.getModal();
                var $backdrop = $modal.data("bs.modal").$backdrop;
                $modal.css("z-index", BootstrapDialog.ZINDEX_MODAL + (dialogCount - 1) * 20);
                $backdrop.css("z-index", BootstrapDialog.ZINDEX_BACKDROP + (dialogCount - 1) * 20)
            }
            return this
        },
        realize: function() {
            this.initModalStuff();
            this.getModal().addClass(BootstrapDialog.NAMESPACE).addClass(this.getType()).addClass(this.getSize()).addClass(this.getCssClass());
            this.getModalFooter().append(this.createFooterContent());
            this.getModalHeader().append(this.createHeaderContent());
            this.getModalBody().append(this.createBodyContent());
            this.getModal().modal({
                backdrop: "static",
                keyboard: false,
                show: false
            });
            this.makeModalDraggable();
            this.handleModalEvents();
            this.setRealized(true);
            this.updateButtons();
            this.updateTitle();
            this.updateMessage();
            this.updateClosable();
            this.updateAnimate();
            return this
        },
        open: function() { ! this.isRealized() && this.realize();
            this.getModal().modal("show");
            this.updateZIndex();
            this.setOpened(true);
            return this
        },
        close: function() {
            this.getModal().modal("hide");
            if (this.isAutodestroy()) {
                delete BootstrapDialog.dialogs[this.getId()]
            }
            this.setOpened(false);
            BootstrapDialog.moveFocus();
            BootstrapDialog.showScrollbar();
            return this
        }
    };
    BootstrapDialog.newGuid = function() {
        return "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g,
        function(c) {
            var r = Math.random() * 16 | 0,
            v = c === "x" ? r: (r & 3 | 8);
            return v.toString(16)
        })
    };
    BootstrapDialog.show = function(options) {
        return new BootstrapDialog(options).open()
    };
    BootstrapDialog.alert = function() {
        var options = {};
        var defaultOptions = {
            type: BootstrapDialog.TYPE_PRIMARY,
            title: null,
            message: null,
            closable: true,
            buttonLabel: "OK",
            callback: null
        };
        if (typeof arguments[0] === "object" && arguments[0].constructor === {}.constructor) {
            options = $.extend(true, defaultOptions, arguments[0])
        } else {
            options = $.extend(true, defaultOptions, {
                message: arguments[0],
                closable: false,
                buttonLabel: "OK",
                callback: typeof arguments[1] !== "undefined" ? arguments[1] : null
            })
        }
        return new BootstrapDialog({
            type: options.type,
            title: options.title,
            message: options.message,
            closable: options.closable,
            data: {
                callback: options.callback
            },
            onhide: function(dialog) { ! dialog.getData("btnClicked") && dialog.isClosable() && typeof dialog.getData("callback") === "function" && dialog.getData("callback")(false)
            },
            buttons: [{
                label: options.buttonLabel,
                action: function(dialog) {
                    dialog.setData("btnClicked", true);
                    typeof dialog.getData("callback") === "function" && dialog.getData("callback")(true);
                    dialog.close()
                }
            }]
        }).open()
    };
    BootstrapDialog.confirm = function(message, callback) {
        return new BootstrapDialog({
            title: "系统提示",
            message: message,
            closable: false,
            data: {
                "callback": callback
            },
            buttons: [{
                label: "取消",
                action: function(dialog) {
                    typeof dialog.getData("callback") === "function" && dialog.getData("callback")(false);
                    dialog.close()
                }
            },
            {
                label: "确认",
                cssClass: "btn-primary",
                action: function(dialog) {
                    typeof dialog.getData("callback") === "function" && dialog.getData("callback")(true);
                    dialog.close()
                }
            }]
        }).open()
    };
    BootstrapDialog.warning = function(message, callback) {
        return new BootstrapDialog({
            type: BootstrapDialog.TYPE_WARNING,
            message: message
        }).open()
    };
    BootstrapDialog.danger = function(message, callback) {
        return new BootstrapDialog({
            type: BootstrapDialog.TYPE_DANGER,
            message: message
        }).open()
    };
    BootstrapDialog.success = function(message, callback) {
        return new BootstrapDialog({
            type: BootstrapDialog.TYPE_SUCCESS,
            message: message
        }).open()
    };
    return BootstrapDialog
}));